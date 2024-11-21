package com.stone.it.rcms.user.service.impl;

import com.alibaba.fastjson2.JSONObject;
import com.stone.it.rcms.core.exception.RcmsApplicationException;
import com.stone.it.rcms.core.util.DateUtil;
import com.stone.it.rcms.core.util.JwtUtils;
import com.stone.it.rcms.core.util.ResponseUtil;
import com.stone.it.rcms.user.dao.ILoginDao;
import com.stone.it.rcms.user.service.ILoginService;
import com.stone.it.rcms.user.vo.AccountSecretVO;
import com.stone.it.rcms.user.vo.AppSecretVO;
import com.stone.it.rcms.user.vo.AuthAccountVO;
import com.stone.it.rcms.user.vo.EnterpriseDetailVO;
import com.stone.it.rcms.user.vo.LoginResponseVO;
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
 * @author cj.stone
 * @Date 2024/10/14
 * @Desc
 */
@Named
public class LoginService implements ILoginService {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginService.class);

    @Inject
    private ILoginDao loginDao;

    @Override
    public LoginResponseVO userLogin(AccountSecretVO userVO) {
        // 登录认证
        String sessionId = subjectLogin(userVO.getAccount(), userVO.getPassword(), "account");
        // 获取用户信息
        AuthAccountVO dbUser = loginDao.findAccountInfoById(userVO.getAccount());
        Calendar expTime = JwtUtils.getExpireTime(60 * 5);
        String accessToken = buildJwtToken(sessionId, userVO.getAccount(), userVO.getPassword(), "app", expTime,
            dbUser.getEnterpriseId());
        String refreshToken = buildJwtToken(sessionId, userVO.getAccount(), userVO.getPassword(), "app",
            JwtUtils.getExpireTime(60 * 10), dbUser.getEnterpriseId());
        LoginResponseVO loginResVO = new LoginResponseVO();
        loginResVO.setAccessToken(accessToken);
        loginResVO.setRefreshToken(refreshToken);
        loginResVO.setUsername(dbUser.getAccountCode());
        loginResVO.setNickname(dbUser.getAccountName());
        String[] roles = (dbUser.getAccountRoles()).split(",");
        ArrayList<String> roleList = new ArrayList<>();
        Collections.addAll(roleList, roles);
        loginResVO.setRoles(roleList);
        ArrayList<String> permissions = new ArrayList<String>();
        if (dbUser.getAccountRoles().contains("platformAdmin")) {
            permissions.add("*:*:*");
        } else {
            permissions.addAll(loginDao.findPermsByRoles(roleList));
        }
        loginResVO.setPermissions(permissions);
        loginResVO.setExpires(DateUtil.formatDate(expTime.getTime(), "yyyy-MM-dd HH:mm:ss"));
        loginResVO.setEnterpriseId(dbUser.getEnterpriseId());
        // 查询当前登录用户的企业信息
        EnterpriseDetailVO extraInfo = loginDao.findEnterpriseDetailById(dbUser.getEnterpriseId());
        loginResVO.setExtraInfo(extraInfo);
        return loginResVO;
    }

    @Override
    public LoginResponseVO userLoginRefresh(LoginResponseVO loginResVO) {
        LoginResponseVO newLoginResVO;
        Map<String, Object> verify = JwtUtils.verifyToken(loginResVO.getRefreshToken());
        if (!(boolean)verify.get("state")) {
            throw new RcmsApplicationException(401, "请求认证已失效", verify.get("msg"));
        }
        Map<String, String> user = JwtUtils.getTokenInfo(loginResVO.getRefreshToken());
        AccountSecretVO userVO = new AccountSecretVO();
        userVO.setAccount(user.get("account"));
        userVO.setPassword(user.get("password"));
        // 先退出上次登录
        userLogout();
        // 重新登录
        LoginResponseVO resVO = userLogin(userVO);
        newLoginResVO = new LoginResponseVO(resVO.getAccessToken(), resVO.getRefreshToken(), resVO.getExpires());
        return newLoginResVO;
    }

    @Override
    public JSONObject appToken(AppSecretVO appSecretVO) {
        String sessionId = subjectLogin(appSecretVO.getAppId(), appSecretVO.getSecret(), "app");
        if (sessionId == null) {
            return null;
        }
        AuthAccountVO dbUser = loginDao.findAccountInfoById(appSecretVO.getAppId());
        JSONObject result = new JSONObject();
        Calendar expTime = JwtUtils.getExpireTime(60 * 30);
        String accessToken = buildJwtToken(sessionId, appSecretVO.getAppId(), appSecretVO.getSecret(), "app", expTime,
            dbUser.getEnterpriseId());
        result.put("Authorization", accessToken);
        result.put("expires", DateUtil.formatDate(expTime.getTime(), "yyyy-MM-dd HH:mm:ss"));
        return result;
    }

    private String buildJwtToken(String sessionId, String account, String password, String type, Calendar instance,
        String enterpriseId) {
        Map<String, String> map = new HashMap<>();
        map.put("sessionId", sessionId);
        map.put("enterpriseId", enterpriseId);
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
            LOGGER.error("user login error.", e);
            return ResponseUtil.responseBuild(HttpStatus.SC_INTERNAL_SERVER_ERROR, "退出失败！", e.getMessage());
        }
    }

    private String subjectLogin(String account, String password, String type) {
        // 查询数据库用户信息
        AuthAccountVO dbUser = loginDao.findAccountInfoById(account);
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
        user.put("enterpriseId", String.valueOf(dbUser.getEnterpriseId()));
        user.put("type", type);
        UsernamePasswordToken token = new UsernamePasswordToken(account, JwtUtils.generateToken(user));
        // 禁用记住我
        token.setRememberMe(false);
        subject.login(token);
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
