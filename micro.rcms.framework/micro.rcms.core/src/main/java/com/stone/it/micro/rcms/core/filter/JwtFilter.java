package com.stone.it.micro.rcms.core.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author iMrJi
 * @Description TODO
 * @Date 2022/8/30 10:28 PM
 * @Version 1.0
 */
public class JwtFilter extends GenericFilterBean {

    private static final Logger LOGGER = LoggerFactory.getLogger(JwtFilter.class);

    /**
     *
     * @param req
     * @param res
     * @param chain
     * @throws IOException
     * @throws ServletException
     */
    public void doFilter(final ServletRequest req, final ServletResponse res, final FilterChain chain)
            throws IOException, ServletException {
        final HttpServletRequest request = (HttpServletRequest) req;
        final HttpServletResponse response = (HttpServletResponse) res;
        final String authHeader = request.getHeader("authorization");
        final String reqUrl = request.getRequestURI();
        LOGGER.info("Request url {}", reqUrl);
        final String query = request.getQueryString();
        LOGGER.info("Request query {}", query);
        chain.doFilter(req, res);
    }
}
