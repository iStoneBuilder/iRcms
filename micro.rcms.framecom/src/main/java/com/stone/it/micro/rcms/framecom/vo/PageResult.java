package com.stone.it.micro.rcms.framecom.vo;

import java.io.Serializable;
import java.util.List;

/**
 * @Author iMrJi
 * @Description TODO
 * @Date 2022/8/26 10:22 PM
 * @Version 1.0
 */
public class PageResult<T> implements Serializable {

  private static final long serialVersionUID = -3422814987475041137L;

  private String code = "success";
  private String desc = "success";

  /**
   * 分页信息
   */
  private PageVO meta;
  /**
   * 对象list
   */
  private List<T> data;


  public PageResult(PageVO meta, List<T> data) {
    this.meta = meta;
    this.data = data;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getDesc() {
    return desc;
  }

  public void setDesc(String desc) {
    this.desc = desc;
  }

  public PageVO getMeta() {
    return meta;
  }

  public void setMeta(PageVO meta) {
    this.meta = meta;
  }

  public List<T> getData() {
    return data;
  }

  public void setData(List<T> data) {
    this.data = data;
  }
}
