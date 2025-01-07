package com.stone.it.rcms.core.pay.service.impl;

import com.stone.it.rcms.core.pay.util.HttpServletUtils;
import com.stone.it.rcms.core.pay.vo.PayOrderVO;
import com.stone.it.rcms.core.pay.vo.PayVO;
import com.stone.it.rcms.core.pay.vo.WxConfigVO;
import com.wechat.pay.java.core.notification.RequestParam;
import com.wechat.pay.java.service.payments.jsapi.model.Amount;
import com.wechat.pay.java.service.payments.jsapi.model.Payer;
import com.wechat.pay.java.service.payments.jsapi.model.PrepayRequest;
import com.wechat.pay.java.service.refund.model.AmountReq;
import com.wechat.pay.java.service.refund.model.CreateRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 *
 * @author cj.stone
 * @Date 2024/12/9
 * @Desc
 */
public class PayBaseService {

    /**
     * 获取预支付请求
     */
    public static PrepayRequest getPrepayRequest(WxConfigVO wxPayConfig, PayVO payVO) {
        PrepayRequest request = new PrepayRequest();
        // appId
        request.setAppid(wxPayConfig.getAppId());
        // 商户ID
        request.setMchid(wxPayConfig.getMerchantId());
        // 商品描述
        request.setDescription(payVO.getProductType());
        // 商户订单号
        request.setOutTradeNo(payVO.getOrderNo());
        // 通知地址
        request.setNotifyUrl(wxPayConfig.getPayNotifyUrl());
        // 金额
        Amount amount = new Amount();
        amount.setTotal(10);
        request.setAmount(amount);
        Payer payer = new Payer();
        // 用户标识(疑问点)
        payer.setOpenid(payVO.getOpenId());
        request.setPayer(payer);
        return request;
    }


    public static RequestParam getRequestParam(HttpServletRequest request) throws IOException {
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
        return new RequestParam.Builder().serialNumber(serial).nonce(nonce).signature(signature).timestamp(timestamp)
                .signType(signType).body(HttpServletUtils.getRequestBody(request)).build();
    }

    public static CreateRequest getRefundRequest(PayOrderVO orderVO, PayVO payVO, WxConfigVO wxPayConfig) {
        CreateRequest request = new CreateRequest();
        // 【商户订单号】 原支付交易对应的商户订单号
        request.setOutTradeNo(payVO.getOrderNo());
        // 【商户退款单号】 商户系统内部的退款单号
        request.setOutRefundNo("REFUND_" + payVO.getOrderNo());
        // 【微信支付订单号】 微信支付交易订单号
        request.setTransactionId(orderVO.getTransactionId());
        // 退款信息(单位分)
        AmountReq amount = new AmountReq();
        amount.setTotal(payVO.getOrderAmount());
        amount.setRefund(payVO.getRefundAmount());
        amount.setCurrency("CNY");
        request.setAmount(amount);
        // 退款回调URL
        request.setNotifyUrl(wxPayConfig.getRefundNotifyUrl());
        return request;
    }
}
