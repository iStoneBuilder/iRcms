package com.stone.it.rcms.auth.config;

import org.apache.shiro.authc.AuthenticationToken;

/**
 *
 * @author cj.stone
 * @Date 2024/10/21
 * @Desc
 */
public class AccountToken implements AuthenticationToken {

    private final String token;

    public AccountToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
