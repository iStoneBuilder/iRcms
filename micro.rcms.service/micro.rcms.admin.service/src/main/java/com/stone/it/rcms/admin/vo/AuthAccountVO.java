package com.stone.it.rcms.admin.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.stone.it.rcms.core.vo.BaseVO;
import lombok.Data;

/**
 * 查询账户，程序详情信息
 * 
 * @author cj.stone
 * @Date 2024/10/21
 * @Desc
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AuthAccountVO extends BaseVO {

    private String enterpriseId;
    private String accountCode;
    private String accountName;
    private String accountType;
    private String password;
    private String accountRoles;

}
