package com.stone.it.rcms.user.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * 登录账户对象
 * 
 * @author cj.stone
 * @Date 2024/11/18
 * @Desc
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountSecretVO {
    private String account;
    private String password;
}
