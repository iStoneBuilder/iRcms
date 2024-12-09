package com.stone.it.rcms.core.pay.service.impl;

import com.stone.it.rcms.core.pay.vo.PayVO;
import com.stone.it.rcms.core.pay.vo.WxConfigVO;
import com.wechat.pay.java.service.payments.jsapi.model.Amount;
import com.wechat.pay.java.service.payments.jsapi.model.Payer;
import com.wechat.pay.java.service.payments.jsapi.model.PrepayRequest;

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
}
