package com.stone.it.rcms.auth.config;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 *
 * @author cj.stone
 * @Date 2024/10/23
 * @Desc
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ConfigurationProperties(prefix = "rcms.auth.shiro")
public class ShiroConfiguration {

    /**
     * 不需要认证授权的路径
     */
    private List<String> ignorePaths;
    /**
     * 需要认证授权的路径
     */
    private List<String> authcPaths;
}
