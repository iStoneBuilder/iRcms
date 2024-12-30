package com.stone.it.rcms.mifi.api.vo;

import lombok.Data;

/**
 * 流量上报VO
 * 
 * @author cj.stone
 * @Date 2024/12/27
 * @Desc
 */
@Data
public class FlowReportVO {

    private String deviceSn;
    // (是)设备使用总流量 （一个流量上报周期内）
    private Long deviceTotalFlow;
    // (是)当前使用卡总流量 （一个流量上报周期内）
    private Long seedTotalFlow;
    // (是)electric
    private int electric;
    // (是)信号强度
    private int signal;
    // (是)设备连接数
    private int deviceConNum;
    // (是)运营商
    private String isp;
    // (是)当前使用的卡 ICCID
    private String usingIccid;
    // 种子卡ICCID
    private String seedIccid;
    // 上报原始数据
    private String reportStr;

}
