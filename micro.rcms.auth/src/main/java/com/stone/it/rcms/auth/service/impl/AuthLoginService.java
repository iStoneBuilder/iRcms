package com.stone.it.rcms.auth.service.impl;

import com.stone.it.rcms.auth.service.IAuthLoginService;
import com.stone.it.rcms.auth.util.JwtUtils;
import com.stone.it.rcms.auth.vo.AccountVO;
import com.stone.it.rcms.auth.vo.AuthUserVO;
import java.util.Map;
import javax.inject.Named;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
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

    @Override
    public String userLogin(AuthUserVO userVO) {
        return subjectLogin(userVO) ? "登录成功" : "用户名或密码错误";
    }

    @Override
    public String userToken(AccountVO accountVO) {
        Map<String, String> map = new java.util.HashMap<>();
        map.put("appId", accountVO.getAppId());
        map.put("secret", accountVO.getSecret());
        return JwtUtils.generateToken(map);
    }

    @Override
    public String userLogout() {
        Subject currentUser = SecurityUtils.getSubject();
        try {
            PrincipalCollection principals = currentUser.getPrincipals();
            if (principals == null) {
                return "用户未登录！";
            }
            AuthUserVO user = (AuthUserVO)principals.getPrimaryPrincipal();
            // 数据库记录日志，执行退出
            currentUser.logout();
            return "退出成功！";
        } catch (Exception e) {
            return "退出失败！";
        }
    }

    private boolean subjectLogin(AuthUserVO userVO) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(userVO.getUserId(), userVO.getPassword());
        try {
            subject.login(token);
            return true;
        } catch (AuthenticationException e) {
            LOGGER.info(e.getMessage());
            return false;
        }
    }
}
