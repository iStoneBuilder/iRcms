package com.stone.it.micro.rcms.config;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author cj.stone
 * @Date 2023/4/27
 * @Desc
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ConfigurationProperties(prefix = "security.gw.referer")
public class RefererConfiguration {


  /**
   * 是否开启filter
   */
  private boolean enabled;

  /**
   * 白名单
   */
  private List<String> whiteList;

  /**
   * 忽略校验路径
   */
  private List<String> ignoreHosts;


}
