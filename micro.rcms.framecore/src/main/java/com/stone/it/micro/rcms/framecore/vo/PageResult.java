package com.stone.it.micro.rcms.framecore.vo;

import java.io.Serializable;
import java.util.List;
import lombok.Data;

/**
 * @Author iMrJi
 * @Description TODO
 * @Date 2022/8/26 10:22 PM
 * @Version 1.0
 */
@Data
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

}
