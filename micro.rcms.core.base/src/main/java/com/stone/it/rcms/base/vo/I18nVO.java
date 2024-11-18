package com.stone.it.rcms.base.vo;

import com.stone.it.rcms.core.vo.BaseVO;
import lombok.Data;

/**
 * @author cj.stone
 * @Desc
 */
@Data
public class I18nVO extends BaseVO {

  private String i18nId;
  private String i18nKey;
  private String i18nName;
  private String language;

}
