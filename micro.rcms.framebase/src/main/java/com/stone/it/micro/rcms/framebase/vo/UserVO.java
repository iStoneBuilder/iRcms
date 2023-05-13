package com.stone.it.micro.rcms.framebase.vo;

import com.stone.it.micro.rcms.framecore.vo.BaseVO;
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
