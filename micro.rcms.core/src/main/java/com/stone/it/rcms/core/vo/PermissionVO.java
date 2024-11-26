package com.stone.it.rcms.core.vo;

import lombok.Data;

/**
 * @author cj.stone
 * @Date 2024/11/1
 * @Desc
 */
@Data
public class PermissionVO extends BaseVO {

    private String code;
    private String name;
    private String path;
    private String method;
    private String type;
    private String authCode;
    private String isOpenApi = "N";
    private String serviceCode;

}
