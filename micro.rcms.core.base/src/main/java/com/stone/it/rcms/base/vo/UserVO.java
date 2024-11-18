package com.stone.it.rcms.base.vo;

import com.stone.it.rcms.core.vo.BaseVO;
import lombok.Data;

/**
 *
 * @author cj.stone
 * @Desc
 */
@Data
public class UserVO extends BaseVO {

  private String userId;
  private String userAccount;

  private String name;
  private String phone;
  private String gender;


}
