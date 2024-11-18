package com.stone.it.rcms.auth.config;

import com.stone.it.rcms.auth.service.IAuthSettingService;
import com.stone.it.rcms.auth.vo.AuthAccountVO;
import com.stone.it.rcms.auth.vo.SystemApiVO;
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
    private IAuthSettingService authSettingService;

    /**
     * 授权认证（生成Token会进入此方法）
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
        return new SimpleAuthenticationInfo(JwtUtils.getTokenInfo(password),
            new SimpleHash("md5", password, userId).toHex(), ByteSource.Util.bytes(userId), getName());
    }

    /**
     * 获取用户角色和权限信息(接口需要权限认证时进入此方法)
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
            // 应用账户直接应用ID关联接口权限
            roleSets.add(userInfo.get("userId"));
        } else {
            AuthAccountVO authAccountVO = authSettingService.getUserInfoByUserId(userInfo.get("userId"));
            List<String> roles = List.of(authAccountVO.getAccountRoles().split(","));
            if (!roles.isEmpty()) {
                roleSets.addAll(roles);
            }
        }
        // 查找用户角色权限
        handleRolePermission(roleSets, authSets);
        info.setRoles(roleSets);
        info.setStringPermissions(authSets);
        return info;
    }

    private void handleRolePermission(Set<String> roleSets, Set<String> authSets) {
        List<SystemApiVO> permissions = authSettingService.getApiPathByRoleCodes(roleSets);
        if (permissions != null && !permissions.isEmpty()) {
            permissions.forEach(t -> {
                authSets.add(t.getAuthCode());
            });
        }
    }

}
