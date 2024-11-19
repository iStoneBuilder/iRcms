package com.stone.it.rcms.user.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;
import lombok.Data;

/**
 *
 * @author cj.stone
 * @Date 2024/11/4
 * @Desc
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoginResponseVO {
    private String enterpriseId;
    private String avatar;
    private String username;
    private String nickname;
    private List<String> roles;
    private List<String> permissions;
    private String accessToken;
    private String refreshToken;
    private String expires;
    private EnterpriseDetailVO extraInfo;

    public LoginResponseVO() {}

    public LoginResponseVO(String accessToken, String refreshToken, String expires) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.expires = expires;
    }
}
