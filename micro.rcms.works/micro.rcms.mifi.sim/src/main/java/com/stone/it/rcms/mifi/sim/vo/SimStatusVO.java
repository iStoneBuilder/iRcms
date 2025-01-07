package com.stone.it.rcms.mifi.sim.vo;

import com.stone.it.rcms.core.vo.BaseVO;
import lombok.Data;

import java.util.Date;

/**
 *
 * @author cj.stone
 * @Date 2024/12/23
 * @Desc
 */
@Data
public class SimStatusVO extends BaseVO {
    private String requestId;
    private String iccid;
    private Date changeTime;
    private String operateType;
    private String orgStatus;
    private String newStatus;
    private String remark;
}
