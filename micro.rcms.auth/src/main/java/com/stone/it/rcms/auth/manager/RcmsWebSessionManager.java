package com.stone.it.rcms.auth.manager;

import com.stone.it.rcms.core.exception.RcmsApplicationException;
import com.stone.it.rcms.core.util.JwtUtils;
import java.io.Serializable;
import java.util.Map;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;

/**
 *
 * @author cj.stone
 * @Date 2024/11/5
 * @Desc
 */
public class RcmsWebSessionManager extends DefaultWebSessionManager {

    @Override
    protected Serializable getSessionId(ServletRequest request, ServletResponse response) {
        HttpServletRequest req = (HttpServletRequest)request;
        Serializable authorization = req.getHeader("Authorization");
        if (authorization != null) {
            // 校验Token合法性
            Map<String, Object> verify = JwtUtils.verifyToken(authorization.toString());
            if (Boolean.FALSE.equals(verify.get("state"))) {
                // Token 校验失败
                throw new RcmsApplicationException(401, verify.get("msg").toString());
            }
            Map<String, String> accountInfo = JwtUtils.getTokenInfo(authorization.toString());
            if (accountInfo.containsKey("sessionId")) {
                return accountInfo.get("sessionId");
            }
        }
        // 如果消息头获取为空，则使用shiro原来的方式获取
        return super.getSessionId(request, response);
    }
}
