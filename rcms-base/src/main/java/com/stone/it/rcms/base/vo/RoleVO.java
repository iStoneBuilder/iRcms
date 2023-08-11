package com.stone.it.rcms.base.vo;


import com.stone.it.rcms.com.vo.BaseVO;
import lombok.Data;

/**
 *
 * @author cj.stone
 * @Date 2023/5/12
 * @Desc
 */
@Data
public class RoleVO extends BaseVO {

  private String roleId;
  private String roleCode;
  private String roleName;
  private String desc;
  
}
