package com.stone.it.micro.rcms.config;

import java.util.List;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author cj.stone
 * @Date 2023/4/27
 * @Desc
 */

@Data
@ConfigurationProperties(prefix = "security.gw.referer")
public class ReferConfiguration {


  /**
   * 是否开启filter
   */
  private boolean enabled;

  /**
   * 忽略校验路径
   */
  private List<String> ignorePaths;

}
