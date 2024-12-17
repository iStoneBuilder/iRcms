package com.stone.it.rcms.core.pay.config;

import com.stone.it.rcms.core.exception.RcmsApplicationException;
import com.stone.it.rcms.core.pay.vo.WxConfigVO;
import com.wechat.pay.java.core.RSAAutoCertificateConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 微信支付证书配置
 *
 * @author cj.stone
 * @Date 2024/12/7
 * @Desc
 */
public class WxPayCertificateConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(WxPayCertificateConfig.class);

    public static void addCertificateConfig(String merchantId, WxConfigVO wxPayConfig) {
        WxPayCertificateManager.getInstance().addCertificateConfig(merchantId, wxPayConfig);
    }

    public static RSAAutoCertificateConfig getCertificateConfig(WxConfigVO wxPayConfig) {
        WxPayCertificateManager manager = WxPayCertificateManager.getInstance();
        RSAAutoCertificateConfig config = manager.getCertificateConfig(wxPayConfig.getMerchantId());
        if (config == null) {
            synchronized (manager) {
                config = manager.getCertificateConfig(wxPayConfig.getMerchantId());
                if (config == null) {
                    try {
                        addCertificateConfig(wxPayConfig.getMerchantId(), wxPayConfig);
                    } catch (Exception e) {
                        LOGGER.error("Failed to add certificate config for merchantId: {}", wxPayConfig.getMerchantId(),
                            e);
                        throw new RcmsApplicationException(500, "微信支付证书配置失败", e.getMessage());
                    }
                    config = manager.getCertificateConfig(wxPayConfig.getMerchantId());
                }
            }
        }
        return config;
    }

}
