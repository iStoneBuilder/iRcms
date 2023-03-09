package com.stone.it.micro.rcms.framebase.vo;

import com.stone.it.micro.rcms.framecore.vo.BaseVO;
import lombok.Data;

/**
 * @author cj.stone
 * @Date 2023/3/9
 * @Desc
 */
@Data
public class SiteVO extends BaseVO {

  private String parentId;
  private String siteId;
  private String siteCode;
  private String siteName;
  private String siteUrl;
  private String isEnabled;
  private String openWay;

}
