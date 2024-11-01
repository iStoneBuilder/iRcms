package com.stone.it.rcms.base.vo;

import com.stone.it.rcms.core.vo.BaseVO;
import lombok.Data;

/**
 *
 * @author cj.stone
 * @Date 2024/11/1
 * @Desc
 */
@Data
public class PermissionVO extends BaseVO {
    private String permissionCode;
    private String apiName;
    private String apiPath;
    private String apiMethod;
    private String apiType;
}
