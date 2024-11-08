package com.stone.it.rcms.core.interceptor;

import com.stone.it.rcms.core.exception.RcmsApplicationException;
import com.stone.it.rcms.core.util.JwtUtils;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import javax.servlet.http.HttpServletRequest;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.message.Message;
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
public class JwtTokenInInterceptor extends RequestParamsInterceptor {

    private static final Logger LOGGER = LoggerFactory.getLogger(JwtTokenInInterceptor.class);

    private static final String[] ANON_PATHS = {"/user/login", "/user/refresh/login", "/user/register", "/user/token"};

    public JwtTokenInInterceptor() {
        super(Phase.RECEIVE);
    }

    @Override
    public void handleMessage(Message message) throws Fault {
        super.handleMessage(message);
        LOGGER.info("CXF Interceptor In ...........");
        HttpServletRequest request = (HttpServletRequest)message.get("HTTP.REQUEST");
        // 不需要认证的接口
        for (String anonPath : ANON_PATHS) {
            if (request.getRequestURI().contains(anonPath)) {
                return;
            }
        }
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
            String requestUri = (String)message.get(Message.REQUEST_URI);
            LOGGER.info("requestUri:{},token:{}", requestUri, token.get(0).toString());
        } else {
            throw new RcmsApplicationException(401, "请求认证已失效", "未传递Authorization Token");
        }
    }
}
