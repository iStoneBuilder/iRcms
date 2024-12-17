package com.stone.it.rcms.core.pay.config;

import com.stone.it.rcms.core.pay.vo.WxConfigVO;
import com.wechat.pay.java.core.RSAAutoCertificateConfig;

import java.util.HashMap;
import java.util.Map;

/**
 * @author jichen
 */
public class WxPayCertificateManager {

    private static final WxPayCertificateManager INSTANCE = new WxPayCertificateManager();

    private final Map<String, RSAAutoCertificateConfig> certificateConfigs = new HashMap<>();

    private WxPayCertificateManager() {}

    public static WxPayCertificateManager getInstance() {
        return INSTANCE;
    }

    public void addCertificateConfig(String merchantId, WxConfigVO wxPayConfig) {
        RSAAutoCertificateConfig config = new RSAAutoCertificateConfig.Builder().merchantId(wxPayConfig.getMerchantId())
            .privateKey(wxPayConfig.getPrivateKey()).merchantSerialNumber(wxPayConfig.getMerchantSerialNumber())
            .apiV3Key(wxPayConfig.getApiV3Key()).build();
        certificateConfigs.put(merchantId, config);
    }

    public RSAAutoCertificateConfig getCertificateConfig(String merchantId) {
        return certificateConfigs.get(merchantId);
    }
}
