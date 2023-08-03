package com.stone.it.micro.rcms.scheduler.vo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import lombok.Data;

/**
 *
 * @author cj.stone
 * @Date 2023/7/27
 * @Desc
 */
@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class QuartzGroupVO {

  private String quartzGroupId;
  private String quartzGroupCode;
  private String quartzGroupName;

  private boolean isAuthorized = false;
  private String authKey;
  private String requestType;
  private String requestPath;
  private String requestBody;

}
