package com.stone.it.rcms.auth.filter;

import com.stone.it.rcms.auth.util.JwtUtils;
import java.util.Map;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import org.apache.cxf.common.util.StringUtils;
import org.apache.shiro.web.filter.authc.AuthenticationFilter;

/**
 * 自定义Token认证过滤器
 * 
 * @author cj.stone
 * @Date 2024/10/21
 * @Desc
 */
public class TokenFilter extends AuthenticationFilter {
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        String path = ((HttpServletRequest)request).getServletPath();
        // 登录和获取Token 放过
        if (path.contains("/auth/login") || path.contains("/auth/token")) {
            return true;
        }
        String token = getRequestToken((HttpServletRequest)request);
        if (!StringUtils.isEmpty(token)) {
            // 验证Token
            Map<String, Object> verifyToken = JwtUtils.verifyToken(token);
            // Token验证成功
            if (Boolean.TRUE.equals(verifyToken.get("state"))) {
                ((HttpServletRequest)request).getSession();
                return true;
            }
        }
        return true;
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        return false;
    }

    private String getRequestToken(HttpServletRequest request) {
        String token = request.getHeader("token");
        if (StringUtils.isEmpty(token)) {
            token = request.getParameter("token");
        }
        return token;
    }

}
