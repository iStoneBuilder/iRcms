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
public class SimDataPlanVO extends BaseVO {
    private String iccid;
    private Date countTime;
    private Double dataPlan;
}
