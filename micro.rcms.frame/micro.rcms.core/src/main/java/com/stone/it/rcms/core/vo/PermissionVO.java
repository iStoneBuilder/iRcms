package com.stone.it.rcms.core.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author cj.stone
 * @Date 2024/11/1
 * @Desc
 */
@Data
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PermissionVO extends BaseVO {

    private String code;
    private String name;
    private String path;
    private String method;
    private String type;
    private String authCode;
    private String isOpenApi;
    private String serviceCode;

}
