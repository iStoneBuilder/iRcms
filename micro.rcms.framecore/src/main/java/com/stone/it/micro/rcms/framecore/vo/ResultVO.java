package com.stone.it.micro.rcms.framecore.vo;

import com.alibaba.fastjson.JSON;
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

  private String code = "success";
  private String desc = "success";
  private String data;
  private String meta;
  public ResultVO() {
  }
  public ResultVO(String code,String desc) {
    this.code = code;
    this.desc = desc;
  }
  public <T> ResultVO(T data) {
    this.data = JSON.toJSONString(data);
  }
  public ResultVO(PageResult pageResult) {
    this.data = JSON.toJSONString(pageResult.getData());
    this.meta = JSON.toJSONString(pageResult.getMeta());
  }
  public <T> ResultVO(String code,String desc,T data,T meta) {
    this.code = code;
    this.desc = desc;
    this.data = JSON.toJSONString(data);
    this.meta = JSON.toJSONString(meta);
  }


}
