package com.stone.it.rcms.core.vo;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @author cj.stone
 * @Desc
 */
@Data
public class BaseVO implements Serializable {

    static final long serialVersionUID = 1L;

    private String enterpriseId;
    private String createBy;
    private String updateBy;
    private Date createDate;
    private Date updateDate;

}
