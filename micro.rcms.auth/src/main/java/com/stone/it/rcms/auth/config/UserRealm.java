package com.stone.it.rcms.auth.config;

import com.stone.it.rcms.auth.service.IUserAuthService;
import com.stone.it.rcms.auth.vo.AuthRoleVO;
import com.stone.it.rcms.auth.vo.PermissionVO;
import com.stone.it.rcms.core.util.JwtUtils;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.inject.Inject;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

/**
 *
 * @author cj.stone
 * @Date 2024/10/18
 * @Desc
 */
public class UserRealm extends AuthorizingRealm {

    @Inject
    private IUserAuthService userAuthService;

    /**
     * 授权认证
     * 
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
        throws AuthenticationException {
        // 采用用户名和密码方式
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken)authenticationToken;
        // 用户id/account
        String userId = usernamePasswordToken.getUsername();
        // token密码
        String password = new String(usernamePasswordToken.getPassword());
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(JwtUtils.getTokenInfo(password),
            new SimpleHash("md5", password, userId).toHex(), ByteSource.Util.bytes(userId), getName());
        return info;
    }

    /**
     * 获取用户角色和权限信息
     * 
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        Map<String, String> userInfo = (Map<String, String>)principalCollection.getPrimaryPrincipal();
        // 用户角色
        Set<String> roleSets = new HashSet<>();
        // 权限信息
        Set<String> authSets = new HashSet<>();
        // 查找用户角色
        if ("app".equals(userInfo.get("type"))) {
            roleSets.add(userInfo.get("userId"));
        } else {
            List<AuthRoleVO> roles = userAuthService.getUserRoleInfoByUserId(userInfo.get("userId"));
            if (roles != null && !roles.isEmpty()) {
                roles.stream().forEach(t -> {
                    roleSets.add(t.getRoleCode());
                });
            }
        }
        // 查找用户角色权限
        List<PermissionVO> permissions = userAuthService.getPermissionByRoleCodes(roleSets);
        if (permissions != null && !permissions.isEmpty()) {
            permissions.stream().forEach(t -> {
                authSets.add(t.getPermissionCode());
            });
        }
        info.setRoles(roleSets);
        info.setStringPermissions(authSets);
        return info;
    }
}
