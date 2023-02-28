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
public class PagedResult<T> implements Serializable {

  private static final long serialVersionUID = -3422814987475041137L;

  /**
   * 分页信息
   */
  private PageVO pageVO;
  /**
   * 对象list
   */
  private List<T> result;

  public PagedResult(PageVO pageVO, List<T> result) {
    this.pageVO = pageVO;
    this.result = result;
  }

}
