package com.stone.it.rcms.core.pay.vo;

import lombok.Data;

/**
 * 微信支付配置类
 * 
 * @author cj.stone
 * @Date 2024/12/7
 * @Desc
 */
@Data
public class WxConfigVO {
    // appId
    private String appId;
    // merchantId
    private String merchantId;
    // 商户API私钥
    private String privateKey;
    // 商户证书序列号
    private String merchantSerialNumber;
    // 商户APIv3密钥
    private String apiV3Key;
    // 支付通知地址
    private String payNotifyUrl;
    // 退款通知地址
    private String refundNotifyUrl;
}
