package com.stone.it.rcms.auth.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author cj.stone
 * @Date 2024/11/5
 * @Desc
 */
public class HeaderFilter implements Filter {

    private static final Logger LOGGER = LoggerFactory.getLogger(HeaderFilter.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
        throws IOException, ServletException {
        // 包装响应
        HttpServletResponse httpResponse = (HttpServletResponse)response;
        HttpServletResponseWrapper responseWrapper = new HttpServletResponseWrapper(httpResponse) {
            @Override
            public void setHeader(String name, String value) {
                LOGGER.info("setHeader: {}={}", name, value);
                if ("Set-Cookie".equalsIgnoreCase(name)) {
                    // 不设置 Set-Cookie 头
                    return;
                }
                super.setHeader(name, value);
            }
        };

        // 继续处理请求
        chain.doFilter(request, responseWrapper);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // 初始化代码
    }

    @Override
    public void destroy() {
        // 清理代码
    }
}