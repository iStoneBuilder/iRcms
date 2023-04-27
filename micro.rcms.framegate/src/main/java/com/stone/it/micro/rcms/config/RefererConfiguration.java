package com.stone.it.micro.rcms.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author cj.stone
 * @Date 2023/4/27
 * @Desc
 */

@Data
@ConfigurationProperties(prefix = "security.gw.referer")
public class RefererConfiguration {


  private boolean enabled;

}
