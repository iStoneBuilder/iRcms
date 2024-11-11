package com.stone.it.rcms.auth.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * @author cj.stone
 * @Date 2024/10/14
 * @Desc
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AuthUserVO {

  private String userId;
  private String userAccount;
  private String userName;
  private String password;
  private String type;

}
