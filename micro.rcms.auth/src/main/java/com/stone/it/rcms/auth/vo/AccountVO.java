package com.stone.it.rcms.auth.vo;

/**
 *
 * @author cj.stone
 * @Date 2024/10/21
 * @Desc
 */
public class AccountVO {
    private String appId;
    private String secret;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

}
