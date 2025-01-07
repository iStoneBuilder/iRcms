package com.stone.it.rcms.core.pay.service.impl;

import com.alibaba.fastjson2.JSONObject;
import com.stone.it.rcms.core.exception.RcmsApplicationException;
import com.stone.it.rcms.core.pay.config.WxPayCertificateConfig;
import com.stone.it.rcms.core.pay.dao.IPayConfigDao;
import com.stone.it.rcms.core.pay.dao.IPayOrderDao;
import com.stone.it.rcms.core.pay.service.IWeChatPayService;
import com.stone.it.rcms.core.pay.service.IWePayNotifyService;
import com.stone.it.rcms.core.pay.vo.PayOrderVO;
import com.stone.it.rcms.core.pay.vo.PayVO;
import com.stone.it.rcms.core.pay.vo.WxConfigVO;
import com.stone.it.rcms.core.util.DateUtil;
import com.wechat.pay.java.core.exception.HttpException;
import com.wechat.pay.java.core.exception.MalformedMessageException;
import com.wechat.pay.java.core.exception.ServiceException;
import com.wechat.pay.java.core.notification.NotificationParser;
import com.wechat.pay.java.core.notification.RequestParam;
import com.wechat.pay.java.service.partnerpayments.jsapi.model.Transaction;
import com.wechat.pay.java.service.payments.jsapi.JsapiServiceExtension;
import com.wechat.pay.java.service.payments.jsapi.model.PrepayRequest;
import com.wechat.pay.java.service.payments.jsapi.model.PrepayWithRequestPaymentResponse;
import com.wechat.pay.java.service.refund.model.RefundNotification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 支付模块
 * 
 * @author cj.stone
 * @Date 2024/12/7
 * @Desc
 */
@Named
public class WeChatPayService extends PayBaseService implements IWeChatPayService, IWePayNotifyService {

    private static final Logger LOGGER = LoggerFactory.getLogger(WeChatPayService.class);

    @Inject
    private IPayOrderDao orderDao;

    @Inject
    private IPayConfigDao configDao;

    /**
     * 预付费支付
     *
     * @param payWay 支付方式（微信支付，平台代付，支付宝支付）
     * @param payVO payVO
     * @return JSONObject
     */
    @Override
    public JSONObject pay(String payWay, String paySource, PayVO payVO) {
        // 生成订单号
        String maiShaOrderNo = "MS" + DateUtil.formatDate();
        payVO.setOrderNo(maiShaOrderNo);
        // 创建平台订单信息
        orderDao.createOrder(new PayOrderVO(payVO, payWay, paySource));
        // 调用支付创建订单
        if ("wechatpay".equals(payWay)) {
            JSONObject backJson = new JSONObject();
            // 物联网系统订单号
            backJson.put("ptOrderNo",maiShaOrderNo);
            PrepayWithRequestPaymentResponse response = createWxPayOrder(payVO, payWay, paySource);
            backJson.put("prepayJson",response);
            return backJson;
        }
        throw new RcmsApplicationException(500, "不支持该下单方式");
    }

    PrepayWithRequestPaymentResponse createWxPayOrder(PayVO payVO, String payWay, String paySource) {
        // 加载支付配置信息(租户ID，支付来源)
        List<WxConfigVO> wxPayConfig = configDao.findWxPayConfigByTpp(payVO.getTenantId(), paySource, payWay);
        JsapiServiceExtension service = new JsapiServiceExtension.Builder()
                .config(WxPayCertificateConfig.getCertificateConfig(wxPayConfig.get(0))).signType("RSA").build();
        PrepayWithRequestPaymentResponse response;
        try {
            PrepayRequest request = getPrepayRequest(wxPayConfig.get(0), payVO);
            LOGGER.info("请求预支付下单，请求参数：{}", JSONObject.toJSONString(request));
            // 调用预下单接口
            response = service.prepayWithRequestPayment(request);
            LOGGER.info("订单【{}】发起预支付成功，返回信息：{}", "orderNo", response);
        } catch (HttpException e) { // 发送HTTP请求失败
            LOGGER.error("微信下单发送HTTP请求失败，错误信息：{}", e.getHttpRequest());
            throw new RcmsApplicationException(500, "下单失败", e);
        } catch (ServiceException e) { // 服务返回状态小于200或大于等于300，例如500
            LOGGER.error("微信下单服务状态错误，错误信息：{}", e.getErrorMessage());
            throw new RcmsApplicationException(500, "下单失败", e.getErrorMessage());
        } catch (MalformedMessageException e) { // 服务返回成功，返回体类型不合法，或者解析返回体失败
            LOGGER.error("服务返回成功，返回体类型不合法，或者解析返回体失败，错误信息：{}", e.getMessage());
            throw new RcmsApplicationException(500, "下单失败", e.getMessage());
        }
        return response;
    }

    @Override
    public String payNotify(String payConfigId, HttpServletRequest request) throws Exception {
        RequestParam requestParam = getRequestParam(request);
        WxConfigVO wxPayConfig = configDao.findWxPayConfigByPci(payConfigId, "");
        // 初始化 NotificationParser
        NotificationParser parser = new NotificationParser(WxPayCertificateConfig.getCertificateConfig(wxPayConfig));
        // 以支付通知回调为例，验签、解密并转换成 Transaction
        LOGGER.info("验签参数：{}", requestParam);
        Transaction transaction = parser.parse(requestParam, Transaction.class);
        LOGGER.info("验签成功！-支付回调结果：{}", transaction.toString());

        Map<String, String> returnMap = new HashMap<>(2);
        returnMap.put("code", "FAIL");
        returnMap.put("message", "失败");
        // 修改订单前，建议主动请求微信查询订单是否支付成功，防止恶意post
        PayOrderVO searchVO = new PayOrderVO();
        searchVO.setOrderNo(transaction.getOutTradeNo());
        PayOrderVO dbOrderVO = orderDao.findOrderDetail(searchVO);
        // 此时订单已经支付成功
        if ("SUCCESS".equals(dbOrderVO.getOrderStatus())) {
            returnMap.put("code", "SUCCESS");
            returnMap.put("message", "成功");
            return JSONObject.toJSONString(returnMap);
        }
        PayOrderVO newOrderVO = new PayOrderVO();
        // 内部订单号
        newOrderVO.setOrderNo(dbOrderVO.getOrderNo());
        // 微信支付订单号
        newOrderVO.setTransactionId(transaction.getTransactionId());
        // 订单状态
        newOrderVO.setOrderStatus(transaction.getTradeState().toString());
        // 不等于支付中
        if (Transaction.TradeStateEnum.USERPAYING != transaction.getTradeState()) {
            LOGGER.info("内部订单号【{}】,微信支付订单号【{}】", transaction.getOutTradeNo(), transaction.getTransactionId());
            // 支付成功
            returnMap.put("code", "SUCCESS");
            returnMap.put("message", "成功");
        }
        // 修改订单信息
        orderDao.updateOrder(newOrderVO);
        // 订单类型为流量套餐&支付成功 => 生成相应的套餐数据
        if ("data_plan".equals(dbOrderVO.getProductType())
            && Transaction.TradeStateEnum.SUCCESS == transaction.getTradeState()) {
            // planEventPublisher.buyDdp(dbOrderVO);
        }
        return JSONObject.toJSONString(returnMap);
    }

    @Override
    public String refundNotify(String payConfigId, HttpServletRequest request) throws Exception {
        try {
            RequestParam requestParam = getRequestParam(request);
            WxConfigVO wxPayConfig = new WxConfigVO();
            // 初始化 NotificationParser
            NotificationParser parser =
                new NotificationParser(WxPayCertificateConfig.getCertificateConfig(wxPayConfig));
            // 以退款通知回调为例，验签、解密并转换成 RefundNotification
            RefundNotification parse = parser.parse(requestParam, RefundNotification.class);
            String refundStatus = parse.getRefundStatus().toString();
            LOGGER.info("退款状态：{}", refundStatus);
            PayOrderVO upVO = new PayOrderVO();
            upVO.setOrderNo(parse.getOutTradeNo());
            upVO.setRefundStatus(parse.getRefundStatus().toString());
            // 更新退款信息
            orderDao.updateOrderRefund(upVO);
            if ("SUCCESS".equals(parse.getRefundStatus().toString())) {
                // planEventPublisher.refund(parse.getOutTradeNo());
            }
        } catch (Exception e) {
            LOGGER.info("退款回调失败！错误信息：{}", e.getMessage());
        }
        return "success";
    }


}
