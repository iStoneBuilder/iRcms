package com.stone.it.rcms.core.interceptor;

import com.stone.it.rcms.core.exception.RcmsApplicationException;
import com.stone.it.rcms.core.util.JwtUtils;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
                throw new RcmsApplicationException(401, "请求认证已失效", verify.get("msg"));
            }
        }
    }
}
