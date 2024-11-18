package com.stone.it.rcms.auth.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * 获取Token程序账户对象
 * 
 * @author cj.stone
 * @Date 2024/11/4
 * @Desc
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AppSecretVO {
    private String appId;
    private String secret;
}
