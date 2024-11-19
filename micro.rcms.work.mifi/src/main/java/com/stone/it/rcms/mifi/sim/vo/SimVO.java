package com.stone.it.rcms.mifi.sim.vo;

import com.stone.it.rcms.core.vo.BaseVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * SIM 卡对象
 * 
 * @author cj.stone
 * @Date 2024/11/19
 * @Desc
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SimVO extends BaseVO {

    private String iccid;
    // 设备SN
    private String deviceSn;
    // 网络类型4G/5G/6G
    private String simType;
    private String simStatus;
    private String isp;
    private String ispName;

}
