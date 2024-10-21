package com.stone.it.rcms.auth.service.impl;

import com.stone.it.rcms.auth.service.IAuthLoginService;
import com.stone.it.rcms.auth.vo.AuthUserVO;
import javax.inject.Named;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;

/**
 *
 * @author cj.stone
 * @Date 2024/10/14
 * @Desc
 */
@Named
public class AuthLoginService implements IAuthLoginService {

    @Override
    public String userLogin(AuthUserVO userVO) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(userVO.getUserId(), userVO.getPassword());
        try {
            subject.login(token);
            return "登录成功";
        } catch (AuthenticationException e) {
            return "用户名或密码错误";
        }
    }
}
