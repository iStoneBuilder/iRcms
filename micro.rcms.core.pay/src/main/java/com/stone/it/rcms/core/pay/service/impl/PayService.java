package com.stone.it.rcms.core.pay.service.impl;

import com.alibaba.fastjson2.JSONObject;
import com.stone.it.rcms.core.exception.RcmsApplicationException;
import com.stone.it.rcms.core.pay.config.WxPayCertificateConfig;
import com.stone.it.rcms.core.pay.dao.IOrderDao;
import com.stone.it.rcms.core.pay.dao.IPayConfigDao;
import com.stone.it.rcms.core.pay.service.IPayService;
import com.stone.it.rcms.core.pay.utils.HttpServletUtils;
import com.stone.it.rcms.core.pay.vo.OrderVO;
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
import com.wechat.pay.java.service.refund.RefundService;
import com.wechat.pay.java.service.refund.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 支付模块
 * 
 * @author cj.stone
 * @Date 2024/12/7
 * @Desc
 */
@Named
public class PayService extends PayBaseService implements IPayService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PayService.class);

    @Inject
    private IOrderDao orderDao;

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
        payVO.setOrderNo("MS" + DateUtil.formatDate());
        // 创建平台订单信息
        orderDao.createOrder(new OrderVO(payVO, payWay, paySource));
        // 调用支付创建订单
        if ("wechatpay".equals(payWay)) {
            PrepayWithRequestPaymentResponse response = createWxPayOrder(payVO, payWay, paySource);
            return JSONObject.parseObject(JSONObject.toJSONString(response));
        }
        throw new RcmsApplicationException(500, "不支持该下单方式");
    }

    @Override
    public String payNotify(String payConfigId, HttpServletRequest request) throws Exception {
        // 请求头Wechat-pay-Signature
        String signature = request.getHeader("Wechatpay-Signature");
        // 请求头Wechat-pay-nonce
        String nonce = request.getHeader("Wechatpay-Nonce");
        // 请求头Wechat-pay-Timestamp
        String timestamp = request.getHeader("Wechatpay-Timestamp");
        // 微信支付证书序列号
        String serial = request.getHeader("Wechatpay-Serial");
        // 签名方式
        String signType = request.getHeader("Wechatpay-Signature-Type");
        // 构造 RequestParam
        RequestParam requestParam = new RequestParam.Builder().serialNumber(serial).nonce(nonce).signature(signature)
            .timestamp(timestamp).signType(signType).body(HttpServletUtils.getRequestBody(request)).build();
        WxConfigVO wxPayConfig = configDao.findWxPayConfigByPci(payConfigId, "");
        // 初始化 NotificationParser
        NotificationParser parser =
            new NotificationParser(WxPayCertificateConfig.rsaAutoCertificateConfig(wxPayConfig));
        // 以支付通知回调为例，验签、解密并转换成 Transaction
        LOGGER.info("验签参数：{}", requestParam);
        Transaction transaction = parser.parse(requestParam, Transaction.class);
        LOGGER.info("验签成功！-支付回调结果：{}", transaction.toString());

        Map<String, String> returnMap = new HashMap<>(2);
        returnMap.put("code", "FAIL");
        returnMap.put("message", "失败");
        // 修改订单前，建议主动请求微信查询订单是否支付成功，防止恶意post
        OrderVO searchVO = new OrderVO();
        searchVO.setOrderNo(transaction.getOutTradeNo());
        OrderVO orderVO = orderDao.findOrderDetail(searchVO);
        // 此时订单已经支付成功
        if ("SUCCESS".equals(orderVO.getOrderStatus())) {
            returnMap.put("code", "SUCCESS");
            returnMap.put("message", "成功");
            return JSONObject.toJSONString(returnMap);
        }
        OrderVO newOrderVO = new OrderVO();
        newOrderVO.setOrderNo(orderVO.getOrderNo());
        newOrderVO.setTransactionId(transaction.getTransactionId());
        newOrderVO.setOrderStatus(transaction.getTradeState().toString());
        // 支付未成功
        if (Transaction.TradeStateEnum.USERPAYING != transaction.getTradeState()) {
            LOGGER.info("内部订单号【{}】,微信支付订单号【{}】", transaction.getOutTradeNo(), transaction.getTransactionId());
            // 支付成功
            newOrderVO.setOrderStatus("2");
            returnMap.put("code", "SUCCESS");
            returnMap.put("message", "成功");
        }
        // 修改订单信息
        orderDao.updateOrder(newOrderVO);
        // 订单类型为流量套餐&支付成功 => 生成相应的套餐数据
        if ("data_plan".equals(orderVO.getProductType())
            && Transaction.TradeStateEnum.SUCCESS != transaction.getTradeState()) {

        }
        return JSONObject.toJSONString(returnMap);
    }

    @Override
    public String refund(PayVO payVO) throws Exception {
        OrderVO searchVO = new OrderVO();
        searchVO.setOrderNo(payVO.getOrderNo());
        OrderVO orderVO = orderDao.findOrderDetail(searchVO);
        try {
            WxConfigVO wxPayConfig = new WxConfigVO();
            // 构建退款service
            RefundService service = new RefundService.Builder()
                .config(WxPayCertificateConfig.rsaAutoCertificateConfig(wxPayConfig)).build();
            CreateRequest request = new CreateRequest();
            // 调用request.setXxx(val)设置所需参数，具体参数可见Request定义
            request.setOutTradeNo(payVO.getOrderNo());
            request.setOutRefundNo("REFUND_" + payVO.getOrderNo());

            AmountReq amount = new AmountReq();
            // 总金额
            amount.setTotal(payVO.getOrderAmount());
            // 退款金额
            amount.setRefund(payVO.getRefundAmount());
            amount.setCurrency("CNY");

            request.setAmount(amount);
            request.setNotifyUrl(wxPayConfig.getRefundNotifyUrl());

            // 接收退款返回参数
            Refund refund = service.create(request);
            LOGGER.info("退款返回信息：{}", refund);
            if (refund.getStatus().equals(Status.SUCCESS)) {
                // 设置退款状态（退款中）
                orderDao.updateOrder(orderVO);
            }
        } catch (ServiceException e) {
            LOGGER.error("退款失败！，错误信息：{}", e.getMessage());
        } catch (Exception e) {
            LOGGER.error("refund服务返回成功，返回体类型不合法，或者解析返回体失败，错误信息：{}", e.getMessage());
        }
        return "success";
    }

    @Override
    public String refundNotify(String payConfigId, HttpServletRequest request) throws Exception {
        try {
            // 请求头Wechatpay-Signature
            String signature = request.getHeader("Wechatpay-Signature");
            // 请求头Wechatpay-nonce
            String nonce = request.getHeader("Wechatpay-Nonce");
            // 请求头Wechatpay-Timestamp
            String timestamp = request.getHeader("Wechatpay-Timestamp");
            // 微信支付证书序列号
            String serial = request.getHeader("Wechatpay-Serial");
            // 签名方式
            String signType = request.getHeader("Wechatpay-Signature-Type");
            // 构造 RequestParam
            RequestParam requestParam =
                new RequestParam.Builder().serialNumber(serial).nonce(nonce).signature(signature).timestamp(timestamp)
                    .signType(signType).body(HttpServletUtils.getRequestBody(request)).build();

            WxConfigVO wxPayConfig = new WxConfigVO();
            // 初始化 NotificationParser
            NotificationParser parser =
                new NotificationParser(WxPayCertificateConfig.rsaAutoCertificateConfig(wxPayConfig));
            // 以支付通知回调为例，验签、解密并转换成 Transaction
            LOGGER.info("refundNotify 验签参数：{}", requestParam);
            RefundNotification parse = parser.parse(requestParam, RefundNotification.class);
            LOGGER.info("验签成功！-退款回调结果：{}", parse.toString());
            String refundStatus = parse.getRefundStatus().toString();
            LOGGER.info("getRefundStatus状态：{}", refundStatus);
            if ("SUCCESS".equals(refundStatus)) {
                LOGGER.info("成功进入退款回调，状态：{}", parse.getRefundStatus());
                orderDao.updateOrder(null);
            }
        } catch (Exception e) {
            LOGGER.info("退款回调失败！错误信息：{}", e.getMessage());
        }
        return "success";
    }

    PrepayWithRequestPaymentResponse createWxPayOrder(PayVO payVO, String payWay, String paySource) {
        // 加载支付配置信息(租户ID，支付来源)
        WxConfigVO wxPayConfig = configDao.findWxPayConfigByTpp(payVO.getTenantId(), paySource);
        JsapiServiceExtension service = new JsapiServiceExtension.Builder()
            .config(WxPayCertificateConfig.rsaAutoCertificateConfig(wxPayConfig)).signType("RSA").build();
        PrepayWithRequestPaymentResponse response;
        try {
            PrepayRequest request = getPrepayRequest(wxPayConfig, payVO);
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

}
