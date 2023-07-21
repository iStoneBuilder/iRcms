package com.stone.it.micro.rcms.framebase.vo;


import com.stone.it.micro.rcms.framecom.vo.BaseVO;
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
