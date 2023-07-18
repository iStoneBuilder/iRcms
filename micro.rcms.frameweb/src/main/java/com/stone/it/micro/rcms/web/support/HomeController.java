package com.stone.it.micro.rcms.web.support;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author cj.stone
 * @Date 2023/7/17
 * @Desc
 */
@Controller
public class HomeController {

  @RequestMapping(value = "/")
  public String homeController(){
    return "websocket/index.html";
  }

}
