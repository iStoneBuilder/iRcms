package com.stone.it.rcms.auth.config;

import com.stone.it.rcms.auth.dao.IShiroAuthDao;
import com.stone.it.rcms.auth.util.AuthLogUtils;
import com.stone.it.rcms.auth.vo.AuthUserVO;
import com.stone.it.rcms.core.util.JwtUtils;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.lang.util.ByteSource;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 *
 * @author cj.stone
 * @Date 2024/10/18
 * @Desc
 */
public class UserRealm extends AuthorizingRealm {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserRealm.class);

    @Inject
    private IShiroAuthDao shiroAuthDao;

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
        // token密码(id,pass,source,type)
        String password = new String(usernamePasswordToken.getPassword());
        Map<String, String> loginInfo = JwtUtils.getTokenInfo(password);
        // 判断账号密码
        AuthUserVO authUserVO = shiroAuthDao.findAccountByIdPassword(userId);
        // 账户密码错误 | 账户类型接口调用错误
        String message = "账号密码错误！";
        if (authUserVO == null) {
            SecurityUtils.getSubject().logout();
            throw new AuthenticationException(message);
        }
        // 获取请求信息
        HttpServletRequest request =
            ((ServletRequestAttributes)Objects.requireNonNull(RequestContextHolder.getRequestAttributes()))
                .getRequest();
        if (!authUserVO.getPassword().equals(loginInfo.get("password"))) {
            SecurityUtils.getSubject().logout();
            // 记录日志
            recordLoginLog(userId, authUserVO, message, false, request);
            throw new AuthenticationException(message);
        }
        if (!authUserVO.getAccountType().equals(loginInfo.get("type"))) {
            SecurityUtils.getSubject().logout();
            message = "account".equals(loginInfo.get("type")) ? "APP账户禁止使用登录接口" : "用户账号禁止使用Token接口";
            recordLoginLog(userId, authUserVO, message, false, request);
            throw new AuthenticationException(message);
        }
        recordLoginLog(userId, authUserVO, "登录成功", true, request);
        return new SimpleAuthenticationInfo(JwtUtils.getTokenInfo(password),
                                            new SimpleHash("md5", password, userId).toHex(), ByteSource.Util.bytes(userId), getName());
    }

    private void recordLoginLog(String userId, AuthUserVO authUserVO, String error, boolean isLogin,
        HttpServletRequest request) {
        String ip = AuthLogUtils.getIpAddr(request);
        String userAgent = request.getHeader("User-Agent");
        String os = AuthLogUtils.getOs(userAgent);
        String browser = AuthLogUtils.getBrowser(userAgent);
        CompletableFuture.runAsync(() -> {
            String location = AuthLogUtils.getLocation(ip);
            // 记录日志
            LOGGER.info("用户: {}, 登录IP: {}, 登录地点: {}, 操作系统: {}, 浏览器类型: {}, 登录状态: {},  登录时间: {}, message: {}", userId, ip,
                location, os, browser, isLogin, new Date(), error);
        });
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
        // 程序账户
        if ("app".equals(userInfo.get("type"))) {
            // 应用账户直接应用ID关联接口权限
            roleSets.add(userInfo.get("userId"));
        } else if ("account".equals(userInfo.get("type"))) {
            // 后台管理账户
            String roleCodes = shiroAuthDao.findAccountRoleById(userInfo.get("userId"));
            if (roleCodes != null) {
                List<String> roles = List.of(roleCodes.split(","));
                if (!roles.isEmpty()) {
                    roleSets.addAll(roles);
                }
            }
        } else if ("user".equals(userInfo.get("type"))) {
            roleSets.add("-default-");
        }
        // 后续增加用户相关代码
        info.setRoles(roleSets);
        HashSet<String> auths = new HashSet<>(shiroAuthDao.findPermsByRoleCodes(new ArrayList<>(roleSets)));
        info.setStringPermissions(auths);
        return info;
    }

}
