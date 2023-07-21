package com.stone.it.micro.rcms.framecom.vo;

import java.util.List;

/**
 * @author cj.stone
 * @Date 2022/12/12
 * @Desc
 */
public class BatchVO<T> extends BaseVO {

  static final long serialVersionUID = 1L;
  /**
   * 新增
   */
  private List<T> createItems;
  /**
   * 更新
   */
  private List<T> updateItems;
  /**
   * 删除
   */
  private List<T> deleteItems;

  public List<T> getCreateItems() {
    return createItems;
  }

  public void setCreateItems(List<T> createItems) {
    this.createItems = createItems;
  }

  public List<T> getUpdateItems() {
    return updateItems;
  }

  public void setUpdateItems(List<T> updateItems) {
    this.updateItems = updateItems;
  }

  public List<T> getDeleteItems() {
    return deleteItems;
  }

  public void setDeleteItems(List<T> deleteItems) {
    this.deleteItems = deleteItems;
  }
}
