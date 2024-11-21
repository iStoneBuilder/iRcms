package com.stone.it.rcms.auth.config;

import com.stone.it.rcms.auth.dao.IShiroAuthDao;
import com.stone.it.rcms.core.util.JwtUtils;
import java.util.ArrayList;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author cj.stone
 * @Date 2024/10/18
 * @Desc
 */
public class UserRealm extends AuthorizingRealm {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserRealm.class);

    @Inject
    private IShiroAuthDao authSettingDao;

    /**
     * 授权认证（生成Token会进入此方法）
     */

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
        throws AuthenticationException {
        LOGGER.info("****** Shiro UserRealm doGetAuthenticationInfo(login)...");
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
        LOGGER.info("****** Shiro UserRealm doGetAuthorizationInfo(auth)...");
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        Map<String, String> userInfo = (Map<String, String>)principalCollection.getPrimaryPrincipal();
        // 用户角色
        Set<String> roleSets = new HashSet<>();
        // 权限信息
        // 程序账户
        if ("app".equals(userInfo.get("type"))) {
            // 应用账户直接应用ID关联接口权限
            roleSets.add(userInfo.get("userId"));
        } else if ("account".equals(userInfo.get("type"))) {
            // 后台管理账户
            String roleCodes = authSettingDao.findAccountRoleById(userInfo.get("userId"));
            if (roleCodes != null) {
                List<String> roles = List.of(roleCodes.split(","));
                if (!roles.isEmpty()) {
                    roleSets.addAll(roles);
                }
            }
        } // 后续增加用户相关代码
        info.setRoles(roleSets);
        HashSet<String> auths = new HashSet<>(authSettingDao.findPermsByRoleCodes(new ArrayList<>(roleSets)));
        info.setStringPermissions(auths);
        return info;
    }

}
