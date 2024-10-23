package com.stone.it.rcms.auth.config;

import com.stone.it.rcms.auth.service.IUserAuthService;
import com.stone.it.rcms.auth.util.JwtUtils;
import com.stone.it.rcms.auth.vo.PermissionVO;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.inject.Inject;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 *
 * @author cj.stone
 * @Date 2024/10/21
 * @Desc
 */
public class AccountRealm extends AuthorizingRealm {
    @Inject
    private IUserAuthService userAuthService;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof AccountToken;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        if (token != null && token.getCredentials() != null) {
            String accountToken = (String)token.getCredentials();
            // 验证accountToken有效性
            Map<String, Object> verifyToken = JwtUtils.verifyToken(accountToken);
            // 校验通过
            if (Boolean.TRUE.equals(verifyToken.get("state"))) {
                // 解析Token获取用户信息
                Map<String, Object> accountInfo = JwtUtils.getTokenInfo(accountToken);
                return new SimpleAuthenticationInfo(accountInfo, accountToken, getName());
            }
        }
        return null;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        Map<String, Object> accountInfo = (Map<String, Object>)principals.getPrimaryPrincipal();
        if (accountInfo != null) {
            if (accountInfo.containsKey("appId")) {
                List<PermissionVO> permissions =
                    userAuthService.getPermissionByAccountId(accountInfo.get("appId").toString());
                // 权限信息
                Set<String> authSets = new HashSet<>();
                if (permissions != null && !permissions.isEmpty()) {
                    permissions.stream().forEach(t -> {
                        authSets.add(t.getPermissionCode());
                    });
                    SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
                    info.setStringPermissions(authSets);
                    return info;
                }
            }
        }
        return null;
    }
}
