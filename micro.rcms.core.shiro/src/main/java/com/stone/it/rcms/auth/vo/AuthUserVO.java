package com.stone.it.rcms.auth.vo;

import lombok.Data;

/**
 *
 * @author cj.stone
 * @Date 2024/11/21
 * @Desc
 */
@Data
public class AuthUserVO {
    private String enterpriseId;
    private String accountCode;
    private String accountName;
    private String accountType;
    private String password;
}
