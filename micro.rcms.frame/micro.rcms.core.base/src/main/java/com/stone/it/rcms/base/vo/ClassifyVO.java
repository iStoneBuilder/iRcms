package com.stone.it.rcms.base.vo;


import com.stone.it.rcms.core.vo.BaseVO;
import java.util.List;
import lombok.Data;

/**
 * @author cj.stone
 * @Desc
 */
@Data
public class ClassifyVO extends BaseVO {

  private String classifyCode;
  private String classifyName;
  private String description;

  private List<ItemVO> items;

}
