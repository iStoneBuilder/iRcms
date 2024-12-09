package com.stone.it.rcms.core.pay.config;

import com.stone.it.rcms.core.pay.vo.WxConfigVO;
import com.wechat.pay.java.core.RSAAutoCertificateConfig;

/**
 * 微信支付证书配置
 * 
 * @author cj.stone
 * @Date 2024/12/7
 * @Desc
 */
public class WxPayCertificateConfig {
    public static RSAAutoCertificateConfig rsaAutoCertificateConfig(WxConfigVO wxPayConfig) {
        return new RSAAutoCertificateConfig.Builder().merchantId(wxPayConfig.getMerchantId())
            .privateKey(wxPayConfig.getPrivateKey()).merchantSerialNumber(wxPayConfig.getMerchantSerialNumber())
            .apiV3Key(wxPayConfig.getApiV3Key()).build();
    }

}
