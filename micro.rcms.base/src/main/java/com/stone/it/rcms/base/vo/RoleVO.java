package com.stone.it.rcms.base.vo;

import com.stone.it.rcms.core.vo.BaseVO;
import lombok.Data;

/**
 *
 * @author cj.stone
 * @Desc
 */
@Data
public class RoleVO extends BaseVO {

  private String roleId;
  private String roleCode;
  private String roleName;
  private String desc;
  
}
