package com.stone.it.rcms.auth.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * 企业（商户）信息
 * 
 * @author cj.stone
 * @Date 2024/11/10
 * @Desc
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class EnterpriseDetailVO {
    private String id;
    private String type;
    private String name;
    private String code;
}
