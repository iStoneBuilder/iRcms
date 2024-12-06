package com.stone.it.rcms.base.vo;

import com.stone.it.rcms.core.vo.BaseVO;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author cj.stone
 * @Desc
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class RoleVO extends BaseVO {

    private String parentId;
    private String id;
    private String code;
    private String name;
    private String description;
    private String merchant;

    private List<RoleVO> children;

}
