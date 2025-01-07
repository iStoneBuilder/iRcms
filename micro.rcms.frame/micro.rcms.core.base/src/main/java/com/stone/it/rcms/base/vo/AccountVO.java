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
public class AccountVO extends BaseVO {

    private String code;
    private String name;
    private String type;
    private String password;
    private String roles;
    private String status;
    private String merchant;
    private String description;

}
