package com.stone.it.rcms.auth.config;

import com.stone.it.rcms.auth.service.IAuthSettingService;
import com.stone.it.rcms.auth.vo.AccountVO;
import com.stone.it.rcms.auth.vo.AuthApisVO;
import com.stone.it.rcms.core.config.CacheService;
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
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author cj.stone
 * @Date 2024/10/18
 * @Desc
 */
public class UserRealm extends AuthorizingRealm {

    @Inject
    private IAuthSettingService authSettingService;

    @Autowired
    private CacheService cacheService;

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
        if (cacheService.getDataFromCache(userInfo.get("userId") + "_roleSets") != null) {
            roleSets.addAll((Set<String>)cacheService.getDataFromCache(userInfo.get("userId") + "_roleSets"));
        } else {
            if ("app".equals(userInfo.get("type"))) {
                roleSets.add(userInfo.get("userId"));
            } else {
                AccountVO accountVO = authSettingService.getUserInfoByUserId(userInfo.get("userId"));
                List<String> roles = List.of(accountVO.getAccountRoles().split(","));
                if (roles != null && !roles.isEmpty()) {
                    roles.stream().forEach(t -> {
                        roleSets.add(t);
                    });
                }
            }
            cacheService.addDataToCache(userInfo.get("userId") + "_roleSets", roleSets);
        }
        if (cacheService.getDataFromCache(userInfo.get("userId") + "_authSets") != null) {
            authSets.addAll((Set<String>)cacheService.getDataFromCache(userInfo.get("userId") + "_authSets"));
        } else {
            // 查找用户角色权限
            List<AuthApisVO> permissions = authSettingService.getApiPathByRoleCodes(roleSets);
            if (permissions != null && !permissions.isEmpty()) {
                permissions.stream().forEach(t -> {
                    authSets.add(t.getAuthCode());
                });
            }
            cacheService.addDataToCache(userInfo.get("userId") + "_authSets", authSets);
        }
        info.setRoles(roleSets);
        info.setStringPermissions(authSets);
        return info;
    }
}
