package com.stone.it.rcms.base.vo;

import com.stone.it.rcms.core.vo.BaseVO;
import lombok.Data;

/**
 * @author cj.stone
 * @Desc
 */
@Data
public class ItemVO extends BaseVO {

  private String classifyCode;

  private String itemId;
  private String itemCode;
  private String itemName;
  private String language;
  private String itemIndex;
  private String isEnabled;
  private String description;

  private String itemAttr1;
  private String itemAttr2;
  private String itemAttr3;
  private String itemAttr4;
  private String itemAttr5;

}
