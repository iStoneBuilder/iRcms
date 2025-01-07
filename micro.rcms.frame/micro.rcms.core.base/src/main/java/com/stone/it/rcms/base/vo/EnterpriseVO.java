package com.stone.it.rcms.base.vo;

import com.stone.it.rcms.core.vo.BaseVO;
import java.util.List;
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
    private String id;
    private String name;
    private String principal;
    private String email;
    private String phone;
    private String remark;
    // '企业类型:platform/enterprise/merchant'
    private String type;
    private int sort;
    private String status;
    // '企业层级:1/2/3/4/5'
    private int level;
    private List<EnterpriseVO> children;

}
