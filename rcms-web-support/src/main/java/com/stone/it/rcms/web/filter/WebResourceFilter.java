package com.stone.it.rcms.web.filter;


import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Web 资源加载器
 * @author cj.stone
 * @Date 2023/7/22
 * @Desc
 */

public class WebResourceFilter implements Filter {

  private static final Logger LOGGER = LoggerFactory.getLogger(WebResourceFilter.class);
  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
      FilterChain filterChain) throws IOException, ServletException {
    final HttpServletRequest request = (HttpServletRequest) servletRequest;
    final HttpServletResponse response =(HttpServletResponse)servletResponse;
    // 当前请求路径
    final String requestUri  = request.getRequestURI();
    // 获取根路径
    final String contextPath = request.getContextPath();
    // 去掉根路径
    final String path_uri = requestUri.replace(contextPath,"");
    LOGGER.info("WebResourceFilter 请求路径 : {}  ",path_uri);
    if(""==path_uri || "/".equals(path_uri)){
      final String index = "/web/rcms/white/index.html";
      request.getRequestDispatcher(index).forward(request,response);
      return;
    }
    filterChain.doFilter(servletRequest,servletResponse);
  }

}
