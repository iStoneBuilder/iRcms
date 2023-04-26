package com.stone.it.micro.rcms.framecore.vo;

import java.io.Serializable;
import lombok.Data;

/**
 * @Author iMrJi
 * @Description TODO
 * @Date 2022/8/26 10:00 PM
 * @Version 1.0
 */

@Data
public class ResultVO implements Serializable {

  private static final long serialVersionUID = -6069252500372952744L;

  private String status;
  private String message;
  private String data;
  private String meta;

  public ResultVO() {
    this.status = "success";
    this.message = "success";
  }


}
