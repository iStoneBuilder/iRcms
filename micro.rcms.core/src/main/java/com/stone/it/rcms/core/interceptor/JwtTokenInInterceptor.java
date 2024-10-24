package com.stone.it.rcms.core.interceptor;

import com.stone.it.rcms.core.exception.RcmsApplicationException;
import com.stone.it.rcms.core.util.JwtUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 入口拦截器 JwtToken认证拦截，需要接口入口配置
 *
 * @Author iMrJi
 * @Description TODO
 * @Version 1.0
 */
public class JwtTokenInInterceptor extends AbstractPhaseInterceptor<Message> {

    private static final Logger LOGGER = LoggerFactory.getLogger(JwtTokenInInterceptor.class);

    public JwtTokenInInterceptor() {
        super(Phase.RECEIVE);
    }

    @Override
    public void handleMessage(Message message) throws Fault {
        LOGGER.info("CXF Interceptor In ...........");
        // header中获取token，校验Token合法性
        TreeMap headers = (TreeMap)message.get(Message.PROTOCOL_HEADERS);
        if (headers.containsKey("Authorization")) {
            ArrayList token = (ArrayList)headers.get("Authorization");
            // 校验Token合法性
            Map<String, Object> verify = JwtUtils.verifyToken(token.get(0).toString());
            if (Boolean.FALSE.equals(verify.get("state"))) {
                // Token 校验失败
                throw new RcmsApplicationException(401, verify.get("msg").toString());
            } else {
                // 转化为账号登录
                Map<String, Object> accountInfo = JwtUtils.getTokenInfo(token.get(0).toString());
                Subject subject = SecurityUtils.getSubject();

                Map<String, String> account = new HashMap<>();
                account.put("userId", accountInfo.get("appId").toString());
                account.put("password", accountInfo.get("secret").toString());
                account.put("type", "app");

                UsernamePasswordToken upToken = new UsernamePasswordToken(accountInfo.get("appId").toString(),
                    JwtUtils.generateToken(account, null));
                subject.login(upToken);
            }
        }
    }
}
