package com.stone.it.rcms.base.vo;


import com.stone.it.rcms.com.vo.BaseVO;
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
