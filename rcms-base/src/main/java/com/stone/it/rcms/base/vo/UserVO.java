package com.stone.it.rcms.base.vo;


import com.stone.it.rcms.com.vo.BaseVO;
import lombok.Data;

/**
 *
 * @author cj.stone
 * @Date 2023/5/13
 * @Desc
 */
@Data
public class UserVO extends BaseVO {

  private String id;
  private String account;
  private String password;

  private String name;
  private String phone;
  private String gender;


}
