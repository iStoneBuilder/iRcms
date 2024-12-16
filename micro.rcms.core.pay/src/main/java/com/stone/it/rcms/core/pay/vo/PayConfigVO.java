package com.stone.it.rcms.core.pay.vo;

import com.stone.it.rcms.core.vo.BaseVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 支付配置VO
 * 
 * @author cj.stone
 * @Date 2024/12/9
 * @Desc
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class PayConfigVO extends BaseVO {
    // 支付配置ID
    private String payConfigId;
    // 支付方式
    private String payWay;
    // 支付类型
    private String payType;
    // 小程序AppID
    private String miniAppId;
    // 小程序AppSecret
    private String miniAppSecret;
    // 公众号AppID
    private String publicAppId;
    // 公众号AppSecret
    private String publicAppSecret;

    // 商户号
    private String mchId;
    // 微信支付V2秘钥
    private String payV2Key;
    // 微信支付V3秘钥
    private String payV3Key;
    // 商户支付证书序列号
    private String mchSerialNumber;
    // 支付回调地址
    private String notifyUrl;
    // 退款回调地址
    private String refundNotifyUrl;
    // 微信支付商户私钥
    private String payPrivateKey;
    // 微信支付商户证书
    private String payCert;
    // 支付证书路径
    private String payCertPath;
}
