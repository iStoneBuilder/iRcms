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
    // 卡商
    private String merchantCode;
    // 设备SN
    private String deviceSn;
    // 卡号运营商
    private String carrierCode;
    // 网络类型4G/5G/6G
    private String netType;
    // 实名状态
    private String nameStatus;
    // 在线状态
    private String onlineStatus;
    // 流量状态
    private String flowStatus;
    // 卡类型 本地卡
    private String simType;
    private String imei;
    // 已使用流量
    private String flowUsed;
    // 剩余流量
    private String flowRemain;
    // 已使用流量(日)
    private String flowUsedDay;
    // 备注
    private String remark;

}
