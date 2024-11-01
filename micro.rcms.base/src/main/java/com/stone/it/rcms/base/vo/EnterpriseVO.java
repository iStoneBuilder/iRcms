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
public class EnterpriseVO extends BaseVO {
    private String parentId;
    private String enterpriseId;
    private String enterpriseCode;
    private String enterpriseName;
    private String enterpriseType;
}
