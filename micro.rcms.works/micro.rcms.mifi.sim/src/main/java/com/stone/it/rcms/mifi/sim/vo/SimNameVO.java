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
public class SimNameVO extends BaseVO {
    private String deviceSn;
    private String iccid;
    private String msisdn;
    private String realName;
    private String authWay;
    private String authStatus;
    private Date authApplyTime;
    private Date authPassTime;
    private Date cleanApplyTime;
    private Date cleanPassTime;
}
