package com.stone.it.rcms.core.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @author cj.stone
 * @Desc
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class BaseVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    // 租户ID
    private String tenantId;
    private String currentEnterpriseId;
    // 企业商户ID
    private String enterpriseId;
    private String createBy;
    private String updateBy;
    private Date createDate;
    private Date updateDate;

}
