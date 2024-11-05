package com.stone.it.rcms.auth.service.impl;

import com.alibaba.fastjson2.JSONObject;
import com.stone.it.rcms.auth.service.IAuthLoginService;
import com.stone.it.rcms.auth.service.IAuthSettingService;
import com.stone.it.rcms.auth.vo.AccountVO;
import com.stone.it.rcms.auth.vo.AppSecretVO;
import com.stone.it.rcms.auth.vo.AuthUserVO;
import com.stone.it.rcms.auth.vo.LoginResVO;
import com.stone.it.rcms.core.exception.RcmsApplicationException;
import com.stone.it.rcms.core.util.DateUtil;
import com.stone.it.rcms.core.util.JwtUtils;
import com.stone.it.rcms.core.util.ResponseUtil;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import javax.inject.Inject;
import javax.inject.Named;
import org.apache.http.HttpStatus;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author cj.stone
 * @Date 2024/10/14
 * @Desc
 */
@Named
public class AuthLoginService implements IAuthLoginService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthLoginService.class);

    @Inject
    private IAuthSettingService authSettingService;

    @Override
    public LoginResVO userLogin(AuthUserVO userVO) {
        // 登录认证
        String sessionId = subjectLogin(userVO.getUserId(), userVO.getPassword(), "account");
        Calendar expTime = JwtUtils.getExpireTime(60 * 5);
        String accessToken = buildJwtToken(sessionId, userVO.getUserId(), userVO.getPassword(), "app", expTime);
        String refreshToken =
            buildJwtToken(sessionId, userVO.getUserId(), userVO.getPassword(), "app", JwtUtils.getExpireTime(60 * 6));
        LoginResVO loginResVO = new LoginResVO();
        loginResVO.setAccessToken(accessToken);
        loginResVO.setRefreshToken(refreshToken);
        // 获取用户信息
        AccountVO dbUser = authSettingService.getUserInfoByUserId(userVO.getUserId());
        loginResVO.setUsername(dbUser.getAccountCode());
        loginResVO.setNickname(dbUser.getAccountName());
        String[] roles = (dbUser.getAccountRoles()).split(",");
        ArrayList<String> roleList = new ArrayList<>();
        Collections.addAll(roleList, roles);
        loginResVO.setRoles(roleList);
        ArrayList<String> permissions = new ArrayList<String>();
        if (dbUser.getAccountRoles().contains("platformAdmin")) {
            permissions.add("*:*:*");
        }
        loginResVO.setPermissions(permissions);
        loginResVO.setExpires(DateUtil.formatDate(expTime.getTime(), "yyyy-MM-dd HH:mm:ss"));
        return loginResVO;
    }

    @Override
    public JSONObject appToken(AppSecretVO appSecretVO) {
        String sessionId = subjectLogin(appSecretVO.getAppId(), appSecretVO.getSecret(), "app");
        if (sessionId == null) {
            return null;
        }
        JSONObject result = new JSONObject();
        Calendar expTime = JwtUtils.getExpireTime(60 * 30);
        String accessToken = buildJwtToken(sessionId, appSecretVO.getAppId(), appSecretVO.getSecret(), "app", expTime);
        result.put("Authorization", accessToken);
        result.put("expires", DateUtil.formatDate(expTime.getTime(), "yyyy-MM-dd HH:mm:ss"));
        return result;
    }

    private String buildJwtToken(String sessionId, String account, String password, String type, Calendar instance) {
        Map<String, String> map = new HashMap<>();
        map.put("sessionId", sessionId);
        map.put("account", account);
        map.put("password", password);
        map.put("type", type);
        return JwtUtils.generateToken(map, instance);
    }

    @Override
    public JSONObject userLogout() {
        Subject currentUser = SecurityUtils.getSubject();
        try {
            PrincipalCollection principals = currentUser.getPrincipals();
            if (principals == null) {
                return ResponseUtil.responseBuild(HttpStatus.SC_INTERNAL_SERVER_ERROR, "您当前未登录！");
            }
            Map<String, String> user = (Map<String, String>)principals.getPrimaryPrincipal();
            // 数据库记录日志，执行退出
            currentUser.logout();
            return ResponseUtil.responseBuild(HttpStatus.SC_OK, "退出成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseUtil.responseBuild(HttpStatus.SC_INTERNAL_SERVER_ERROR, "退出失败！", e.getMessage());
        }
    }

    private String subjectLogin(String account, String password, String type) {
        // 查询数据库用户信息
        AccountVO dbUser = authSettingService.getUserInfoByUserId(account);
        if (dbUser == null) {
            throw new RcmsApplicationException(500, "账号/密码错误！");
        }
        if (!dbUser.getPassword().equals(password)) {
            throw new RcmsApplicationException(500, "账号/密码错误！");
        }
        if (!dbUser.getAccountType().equals(type)) {
            throw new RcmsApplicationException(500, "账号/密码错误！");
        }
        Subject subject = SecurityUtils.getSubject();
        Map<String, String> user = new HashMap<>();
        user.put("userId", dbUser.getAccountCode());
        user.put("password", dbUser.getPassword());
        user.put("type", type);
        subject.login(new UsernamePasswordToken(account, JwtUtils.generateToken(user)));
        return getSessionId(subject);
    }

    private String getSessionId(Subject subject) {
        // 如果当前Subject已经认证，即已经有会话
        if (subject.isAuthenticated()) {
            // 获取当前会话
            Session session = subject.getSession(false);
            if (session != null) {
                // 获取会话ID
                return (String)session.getId();
            }
        }
        return null;
    }
}
