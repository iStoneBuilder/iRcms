package com.stone.it.rcms.base.vo;

import com.stone.it.rcms.core.vo.BaseVO;
import lombok.Data;

/**
 *
 * @author cj.stone
 * @Date 2024/11/2
 * @Desc
 */
@Data
public class AppAccountVO extends BaseVO {

    private String accountCode;
    private String accountName;
    private String accountType;
    private String password;
    private String accountRoles;

}
