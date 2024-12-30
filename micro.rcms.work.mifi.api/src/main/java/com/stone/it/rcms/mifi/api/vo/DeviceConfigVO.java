package com.stone.it.rcms.mifi.api.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

/**
 * 设备配置VO
 * 
 * @author cj.stone
 * @Date 2024/12/27
 * @Desc
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DeviceConfigVO {

    // 请求成功标志 true/false
    private boolean success = true;
    // 商户KEY
    private String merchantKey;
    // 心跳时长 单位s
    private int heartBeatTimes;
    // 流量上报间隔时长 单位s
    private int flowUploadTimes;
    // wifi名称
    private String wifiName;
    // wifi密码
    private String wifiPwd;
    // 是否激活 0 未激活 1 激活
    private int isActive;
    // 设备是否开启 WiFi 0 关闭 1 开启
    private int openWifi;
    // 是否隐藏wifi 0 隐藏 1 不隐藏
    private int hideWifi;
    // 设备是否开启极速上网 0 关闭 1 开启
    private int openQuickNet;
    // 本地卡信息
    private List<SimCardVO> seedCard;
}
