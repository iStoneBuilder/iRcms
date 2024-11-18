package com.stone.it.rcms.auth.vo;

import lombok.Data;

/**
 * 登录账户详细信息
 * 
 * @author cj.stone
 * @Date 2024/11/10
 * @Desc
 */
@Data
public class ResDetailVO {
    private String id;
    private String type;
    private String name;
    private String code;
}
