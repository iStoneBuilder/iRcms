package com.stone.it.rcms.core.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * @author cj.stone
 * @Desc
 */
public class BaseVO implements Serializable {

    static final long serialVersionUID = 1L;

    private String enterpriseId;
    private String createBy;
    private String updateBy;
    private Date createDate;
    private Date updateDate;

    public String getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(String enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

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
