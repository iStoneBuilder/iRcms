package com.stone.it.micro.rcms.framecore.vo;

import java.util.List;
import lombok.Data;

/**
 * @author cj.stone
 * @Date 2022/12/12
 * @Desc
 */
@Data
public class BatchVO extends BaseVO {

  static final long serialVersionUID = 1L;
  /**
   * 插入
   */
  private List<Object> insertItems;
  /**
   * 更新
   */
  private List<Object> updateItems;
  /**
   * 删除
   */
  private List<Object> deleteItems;

}
