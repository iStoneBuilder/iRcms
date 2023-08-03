package com.stone.it.rcms.com.vo;

import java.io.Serializable;

/**
 * @author cj.stone
 * @Date 2022/12/14
 * @Desc
 */
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

  public int getTotalRows() {
    return totalRows;
  }

  public void setTotalRows(int totalRows) {
    this.totalRows = totalRows;
  }

  public int getTotalPage() {
    return totalPage;
  }

  public void setTotalPage(int totalPage) {
    this.totalPage = totalPage;
  }

  public int getCurPage() {
    return curPage;
  }

  public void setCurPage(int curPage) {
    this.curPage = curPage;
  }

  public int getPageSize() {
    return pageSize;
  }

  public void setPageSize(int pageSize) {
    this.pageSize = pageSize;
  }

  public int getStartIndex() {
    return startIndex;
  }

  public void setStartIndex(int startIndex) {
    this.startIndex = startIndex;
  }

  public int getEndIndex() {
    return endIndex;
  }

  public void setEndIndex(int endIndex) {
    this.endIndex = endIndex;
  }
}
