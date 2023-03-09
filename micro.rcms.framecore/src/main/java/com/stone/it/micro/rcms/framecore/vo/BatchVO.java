package com.stone.it.micro.rcms.framecore.vo;

import java.util.List;
import lombok.Data;

/**
 * @author cj.stone
 * @Date 2022/12/12
 * @Desc
 */
@Data
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

}
