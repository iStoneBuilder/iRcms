package com.stone.it.rcms.auth.vo;

import lombok.Data;

/**
 *
 * @author cj.stone
 * @Date 2024/10/21
 * @Desc
 */
@Data
public class AccountVO {

    private String enterpriseId;
    private String accountCode;
    private String accountName;
    private String accountType;
    private String password;
    private String accountRoles;

}
