package com.stone.it.rcms.auth.vo;

import java.util.ArrayList;
import lombok.Data;

/**
 *
 * @author cj.stone
 * @Date 2024/11/4
 * @Desc
 */
@Data
public class LoginResVO {
    private String enterpriseId;
    private String avatar;
    private String username;
    private String nickname;
    private ArrayList<String> roles;
    private ArrayList<String> permissions;
    private String accessToken;
    private String refreshToken;
    private String expires;
    private ResDetailVO extraInfo;

    public LoginResVO() {}

    public LoginResVO(String accessToken, String refreshToken, String expires) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.expires = expires;
    }
}
