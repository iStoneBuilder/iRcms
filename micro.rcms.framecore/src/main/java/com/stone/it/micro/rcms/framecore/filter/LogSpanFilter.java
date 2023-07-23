package com.stone.it.micro.rcms.framecore.filter;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.GenericFilterBean;

/**
 * 日志拦截
 * @Author iMrJi
 * @Description TODO
 * @Date 2022/8/30 10:28 PM
 * @Version 1.0
 */
public class LogSpanFilter extends GenericFilterBean {

  private static final Logger LOGGER = LoggerFactory.getLogger(LogSpanFilter.class);

  /**
   * @param req
   * @param res
   * @param chain
   * @throws IOException
   * @throws ServletException
   */
  @Override
  public void doFilter(final ServletRequest req, final ServletResponse res, final FilterChain chain)
      throws IOException, ServletException {
    LOGGER.info("LogSpanFilter start ... ");
    final HttpServletRequest request = (HttpServletRequest) req;
    final HttpServletResponse response = (HttpServletResponse) res;
    final String authHeader = request.getHeader("authorization");
    final String reqUrl = request.getRequestURI();
    LOGGER.info("LogSpanFilter reqUrl ... {}",reqUrl);
    final String query = request.getQueryString();
    chain.doFilter(req, res);
  }

}
