package com.stone.it.rcms.auth.vo;

import com.stone.it.rcms.core.vo.BaseVO;
import lombok.Data;

/**
 *
 * @author cj.stone
 * @Date 2024/11/1
 * @Desc
 */
@Data
public class AuthApisVO extends BaseVO {
    private String authCode;
    private String apiCode;
    private String apiName;
    private String apiPath;
    private String apiMethod;
    private String apiType;
}
