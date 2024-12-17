package com.stone.it.rcms.user.service.impl;

import com.stone.it.rcms.core.util.JwtUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * 公共部分代码提取
 * 
 * @author cj.stone
 * @Date 2024/12/17
 * @Desc
 */
public class BaseService {

    public String subjectLogin(String account, String password, String type) {
        Subject subject = SecurityUtils.getSubject();
        Map<String, String> user = new HashMap<>();
        user.put("userId", account);
        user.put("password", password);
        user.put("type", type);
        UsernamePasswordToken token = new UsernamePasswordToken(account, JwtUtils.generateToken(user));
        // 禁用记住我
        token.setRememberMe(false);
        subject.login(token);
        return getSessionId(subject);
    }

    public String getSessionId(Subject subject) {
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

    public String buildJwtToken(String sessionId, String account, String tenantId, String type, Calendar instance,
        String enterpriseId, String password) {
        Map<String, String> map = new HashMap<>();
        map.put("sessionId", sessionId);
        map.put("enterpriseId", enterpriseId);
        map.put("tenantId", tenantId);
        map.put("account", account);
        map.put("password", password);
        map.put("type", type);
        return JwtUtils.generateToken(map, instance);
    }
}
