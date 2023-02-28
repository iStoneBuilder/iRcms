package com.stone.it.micro.rcms.ircm.vo;

import com.stone.it.rcms.base.vo.BaseVO;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import lombok.Data;

/**
 * @author cj.stone
 * @Date 2022/12/14
 * @Desc
 */
@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class ItemVO extends BaseVO {

  private String itemId;
  @Size(min = 5, max = 20)
  private String itemCode;
  @Size(min = 1, max = 100)
  private String itemName;
  @NotEmpty
  private String language;
  private String isEnabled;
  private String itemIndex;
  /**
   * 扩张属性
   */
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
