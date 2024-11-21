package com.stone.it.rcms.core.filter;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.GenericFilterBean;

/**
 * 接口日志拦截
 *
 * @Author iMrJi
 * @Description TODO
 * @Version 1.0
 */
public class LogSpanFilter extends GenericFilterBean {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogSpanFilter.class);

    /**
     * @param req req
     * @param res req
     * @param chain req
     * @throws IOException req
     * @throws ServletException req
     */
    @Override
    public void doFilter(final ServletRequest req, final ServletResponse res, final FilterChain chain)
        throws IOException, ServletException {
        final HttpServletRequest request = (HttpServletRequest)req;
        // 当前请求路径
        final String requestUri = request.getRequestURI();
        // 获取根路径
        final String contextPath = request.getContextPath();
        // 去掉根路径
        final String pathUri = requestUri.replace(contextPath, "");
        if (!requestUri.contains("/web/")) {
            // 日志记录
            LOGGER.info("LogSpanFilter 请求路径 {}  {}", pathUri, contextPath);
        }
        chain.doFilter(req, res);
    }

}
