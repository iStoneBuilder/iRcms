package com.stone.it.rcms.base.vo;

import com.stone.it.rcms.com.vo.BaseVO;
import lombok.Data;

/**
 * @author cj.stone
 * @Date 2023/3/9
 * @Desc
 */
@Data
public class I18nVO extends BaseVO {

  private String i18nId;
  private String i18nKey;
  private String i18nName;
  private String language;

}
