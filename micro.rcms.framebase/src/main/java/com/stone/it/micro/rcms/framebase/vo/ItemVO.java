package com.stone.it.micro.rcms.framebase.vo;


import com.stone.it.micro.rcms.framecom.vo.BaseVO;
import lombok.Data;

/**
 * @author cj.stone
 * @Date 2023/3/9
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
  private String itemAttr6;
  private String itemAttr7;
  private String itemAttr8;
  private String itemAttr9;

}
