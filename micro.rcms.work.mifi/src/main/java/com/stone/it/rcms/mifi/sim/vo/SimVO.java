package com.stone.it.rcms.mifi.sim.vo;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.stone.it.rcms.core.vo.BaseVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

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
    // 物联网号
    private String msisdn;
    // 已使用流量
    private Double flowUsed;
    // 剩余流量
    private Double flowRemain;
    // 已使用流量(日)
    private Double flowUsedDay;
    // 备注
    private String remark;

    // 运营商卡信息
    private JSONObject carrierInfo;
    // 用量统计信息
    private List<SimDataPlanVO> usageInfo;
    // 状态变更记录
    private List<SimStatusVO> statusChangeInfo;

}
