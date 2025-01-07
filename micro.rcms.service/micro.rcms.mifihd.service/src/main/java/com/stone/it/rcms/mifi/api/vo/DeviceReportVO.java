package com.stone.it.rcms.mifi.api.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * 设备信息
 * 
 * @author cj.stone
 * @Date 2024/12/27
 * @Desc
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DeviceReportVO {

    private String operateId;
    // 设备SN
    private String deviceSn;
    // wifi名称
    private String wifiName;
    // wifi密码
    private String wifiPwd;
    // 应用包名
    private String packageName;
    // 应用版本
    private String appVersion;
    // 应用版本名称
    private String appVersionName;
    // 电量
    private int electric;
    // 信号强度
    private int signal;
    // 设备连接数
    private int deviceConNum;
    // 运营商
    private String isp;
    // 本地卡信息
    private SimCardVO seedCard;
    // 当前使用种子卡 ICCID
    private String seedCardIccid;
    // 当前使用种子卡 IMSI
    private String seedCardImsi;
    // 当前使用种子卡 IMEI
    private String seedCardImei;

    private String reportStr;
}
