package com.stone.it.rcms.scheduler.vo;

import com.stone.it.rcms.core.vo.BaseVO;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import lombok.Data;

/**
 *
 * @author cj.stone
 * @Desc
 */
@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class QuartzGroupVO extends BaseVO {

  private String quartzGroupId;
  /** 分组编码 */
  private String quartzGroupCode;
  /** 分组名称 */
  private String quartzGroupName;

  /** 是否需要认证 */
  private String isAuthorized;

  /** 返回值key */
  private String authKey;
  /** 请求类型  */
  private String requestType;
  /** 请求地址 */
  private String requestPath;
  /** 请求参数 */
  private String requestParams;
  /** 请求头参数 */
  private String requestHeaders;

}
