package com.stone.it.rcms.auth.service.impl;

import com.alibaba.fastjson2.JSON;
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
import java.util.Date;
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
        JSONObject loginInfo = subjectLogin(userVO, "account");
        LoginResVO loginResVO = new LoginResVO();
        loginResVO.setAccessToken(loginInfo.getString("accessToken"));
        loginResVO.setRefreshToken(loginInfo.getString("refreshToken"));
        AccountVO accountVO = JSON.toJavaObject(loginInfo.getJSONObject("userInfo"), AccountVO.class);
        loginResVO.setUsername(accountVO.getAccountCode());
        loginResVO.setNickname(accountVO.getAccountName());
        String[] roles = (accountVO.getAccountRoles()).split(",");
        ArrayList<String> roleList = new ArrayList<>();
        Collections.addAll(roleList, roles);
        loginResVO.setRoles(roleList);
        ArrayList<String> permissions = new ArrayList<String>();
        if (accountVO.getAccountRoles().contains("platformAdmin")) {
            permissions.add("*:*:*");
        }
        loginResVO.setPermissions(permissions);
        loginResVO.setExpires(DateUtil.formatDate(loginInfo.getDate("expireTime"), "yyyy-MM-dd HH:mm:ss"));
        return loginResVO;
    }

    @Override
    public JSONObject appToken(AppSecretVO appSecretVO) {
        AuthUserVO authUserVO = new AuthUserVO();
        authUserVO.setUserId(appSecretVO.getAppId());
        authUserVO.setPassword(appSecretVO.getSecret());
        JSONObject subJson = subjectLogin(authUserVO, "app");
        JSONObject result = new JSONObject();
        Map<String, String> info = JwtUtils.getTokenInfo(subJson.getString("accessToken"));
        long exp = Long.valueOf(info.get("exp"));
        info.put("sessionId", result.getString("sessionId"));
        info.remove("exp");
        result.put("Authorization", JwtUtils.generateTokenDate(info, new Date(exp)));
        return result;
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

    private JSONObject subjectLogin(AuthUserVO userVO, String type) {
        // 查询数据库用户信息
        AccountVO dbUser = authSettingService.getUserInfoByUserId(userVO.getUserId());
        if (dbUser == null) {
            throw new RcmsApplicationException(500, "账号/密码错误！");
        }
        if (!dbUser.getPassword().equals(userVO.getPassword())) {
            throw new RcmsApplicationException(500, "账号/密码错误！");
        }
        if (!dbUser.getAccountType().equals(type)) {
            throw new RcmsApplicationException(500, "账号/密码错误！");
        }
        JSONObject result = new JSONObject();
        Subject subject = SecurityUtils.getSubject();
        Map<String, String> user = new HashMap<>();
        user.put("userId", dbUser.getAccountCode());
        user.put("password", dbUser.getPassword());
        user.put("type", type);
        Calendar instance = JwtUtils.getExpireTime(type.equals("account") ? 60 * 5 : 60 * 30);
        String token = JwtUtils.generateToken(user, instance);
        result.put("refreshToken", JwtUtils.generateToken(user, JwtUtils.getExpireTime(60 * 6)));
        subject.login(new UsernamePasswordToken(userVO.getUserId(), token));
        result.put("sessionId", getSessionId(subject));
        result.put("accessToken", token);
        result.put("userInfo", dbUser);
        result.put("expireTime", instance.getTime());
        return result;
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
