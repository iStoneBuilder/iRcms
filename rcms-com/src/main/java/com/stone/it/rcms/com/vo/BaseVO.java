package com.stone.it.rcms.com.vo;

import java.io.Serializable;
import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 * @author cj.stone
 * @Date 2022/12/12
 * @Desc
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class BaseVO implements Serializable {

  static final long serialVersionUID = 1L;

  private String createBy;
  private String updateBy;
  private Date createDate;
  private Date updateDate;

  public String getCreateBy() {
    return createBy;
  }

  public void setCreateBy(String createBy) {
    this.createBy = createBy;
  }

  public String getUpdateBy() {
    return updateBy;
  }

  public void setUpdateBy(String updateBy) {
    this.updateBy = updateBy;
  }

  public Date getCreateDate() {
    return createDate;
  }

  public void setCreateDate(Date createDate) {
    this.createDate = createDate;
  }

  public Date getUpdateDate() {
    return updateDate;
  }

  public void setUpdateDate(Date updateDate) {
    this.updateDate = updateDate;
  }
}
