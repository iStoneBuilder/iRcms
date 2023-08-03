package com.stone.it.micro.rcms.web.interceptor;

import com.stone.it.micro.rcms.common.utils.YamlUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 *
 * @author cj.stone
 * @Date 2023/7/22
 * @Desc
 */
public class LoginInterceptor implements HandlerInterceptor {

  private static final Logger LOGGER = LoggerFactory.getLogger(LoginInterceptor.class);

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    LOGGER.info("拦截的请求路径是: {}",request.getRequestURI());
    // 登录拦截 需要在执行之前
    HttpSession session = request.getSession();
    Object loginUser = session.getAttribute("loginUser");
    if (loginUser != null) {
      return true;
    }
    // 阻止，并重定向到登录页
    response.sendRedirect(YamlUtil.getValue("rcms.sso.login")+"?redirect="+request.getRequestURL());
    return true;
  }

}
