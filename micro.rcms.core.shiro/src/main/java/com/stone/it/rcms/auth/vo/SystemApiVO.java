package com.stone.it.rcms.auth.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.stone.it.rcms.core.vo.BaseVO;
import lombok.Data;

/**
 * 接口扫描注册实体
 * 
 * @author cj.stone
 * @Date 2024/11/1
 * @Desc
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SystemApiVO extends BaseVO {
    private String authCode;
    private String apiCode;
    private String apiName;
    private String apiPath;
    private String apiMethod;
    private String apiType;
    private String openApi = "N";
}
