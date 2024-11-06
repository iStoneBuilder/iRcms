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
    private long parentId;
    private long id;
    private String code;
    private String name;
    private String principal;
    private String email;
    private String phone;
    private String remark;
    // '企业类型:platform/enterprise/merchant'
    private String type;
    private int sort;
    private String status;

}
