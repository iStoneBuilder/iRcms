package com.stone.it.rcms.base.vo;

import java.io.Serializable;
import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import lombok.Data;

/**
 * @author cj.stone
 * @Date 2022/12/12
 * @Desc
 */
@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class BaseVO implements Serializable {

  static final long serialVersionUID = 1L;

  private String createBy;
  private String updateBy;
  private Date createDate;
  private Date updateDate;

}
