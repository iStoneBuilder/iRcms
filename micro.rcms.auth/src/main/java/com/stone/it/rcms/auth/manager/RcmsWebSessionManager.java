package com.stone.it.rcms.auth.manager;

import com.stone.it.rcms.core.util.JwtUtils;
import java.io.Serializable;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger LOGGER = LoggerFactory.getLogger(RcmsWebSessionManager.class);

    @Override
    protected Serializable getSessionId(ServletRequest request, ServletResponse response) {
        LOGGER.info("Handle authentication sessionId .....");
        HttpServletRequest req = (HttpServletRequest)request;
        Serializable authorization = req.getHeader("Authorization");
        if (authorization != null) {
            Map<String, String> accountInfo = JwtUtils.getTokenInfo(authorization.toString());
            if (accountInfo.containsKey("sessionId")) {
                return accountInfo.get("sessionId");
            }
        }
        // 如果消息头获取为空，则使用shiro原来的方式获取
        return super.getSessionId(request, response);
    }
}
