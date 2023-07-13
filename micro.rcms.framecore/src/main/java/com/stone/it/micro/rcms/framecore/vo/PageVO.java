package com.stone.it.micro.rcms.framecore.vo;

import com.stone.it.micro.rcms.framecore.config.PageConfig;
import java.io.Serializable;
import lombok.Data;

/**
 * @author cj.stone
 * @Date 2022/12/14
 * @Desc
 */
@Data
public class PageVO implements Serializable {

  private static final long serialVersionUID =  1L;

  /**
   * 总记录数
   */
  private int totalRows;
  /**
   * 总页面数
   */
  private int totalPage;
  /**
   * 当前页数
   */
  private int curPage;
  /**
   * 每页条数
   */
  private int pageSize = PageConfig.DEFAULT.getDefaultPage();
  /**
   * 起始
   */
  private int startIndex;
  /**
   * 结束
   */
  private int endIndex;

}
