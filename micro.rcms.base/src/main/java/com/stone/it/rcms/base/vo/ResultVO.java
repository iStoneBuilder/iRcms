package com.stone.it.rcms.base.vo;

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
    status = "success";
    message = "success";
  }

}
