package com.stone.it.rcms.mifi.api.vo;

/**
 * SIM 卡信息
 * 
 * @author cj.stone
 * @Date 2024/12/27
 * @Desc
 */
public class SimCardVO {

    private String iccid;
    private String imsi;
    private String imei;
    // 启动卡序号
    private String sequence;
    // 极速上网 0.不支持，1.支持
    private int quickNetStatus;
    // 种子卡联网状态 0：联网失败 1：联网成功
    private int networkStatus;
    // 信号值
    private int signal;
    // 默认使用的种子卡
    private int defUsed;
    // 错误描述
    private String msg;
}
