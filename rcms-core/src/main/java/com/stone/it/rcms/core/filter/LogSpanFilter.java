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
    final HttpServletRequest request = (HttpServletRequest) req;
    // 当前请求路径
    final String requestUri  = request.getRequestURI();
    // 获取根路径
    final String contextPath = request.getContextPath();
    // 去掉根路径
    final String path_uri = requestUri.replace(contextPath,"");
    if(isResourceRequest(path_uri)){
      // 静态资源跳过日志记录
    } else {
      LOGGER.info("LogSpanFilter 请求路径 {}  ",path_uri);
    }
    chain.doFilter(req, res);
  }

  private boolean isResourceRequest(String requestUri){
    return ""==requestUri || "/".equals(requestUri) || requestUri.indexOf("/web/")>-1;
  }

}
