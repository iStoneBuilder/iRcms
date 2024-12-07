package com.stone.it.rcms.mifi.device.vo;

import com.stone.it.rcms.core.vo.BaseVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * @author cj.stone
 * @Date 2024/11/19
 * @Desc
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class DeviceVO extends BaseVO {
    // 设备SN
    private String deviceSn;
    // IMEI
    private String imei;
    // 上网模式
    private String netMode;
    // 在线？
    private String online;
    // 设备类型
    private String deviceType;
    // 设备组
    private String deviceGroup;
    // 选卡策略
    private String cardStrategy;
    // 流量模式
    private String flowMode;
    // 入库批次号
    private String batchNo;
    // 检测状态
    private String checkStatus;
    // 设备状态
    private String deviceStatus;
    // 测试流量
    private String testFlow;
    // 备注
    private String remark;
    // 贴片卡物联网号
    private String msisdn1;
    // 贴片卡物联网号2
    private String msisdn2;
    // 贴片卡ICCID
    private String iccid;
    // 贴片卡2ICCID
    private String iccid2;
    // wifi名称
    private String wifiName;
    // wifi密码
    private String wifiPwd;
    // wifi名称5G
    private String wifiName5G;
    // wifi密码5G
    private String wifiPwd5G;

    // 激活用户
    private String activeUser;
    // 激活时间
    private String activeTime;
    // 设备能力
    private String deviceAbility;
    // wifi连接数
    private String wifiConnectNum;
    // 本地卡模式
    private String localCardMode;
    // 软件版本号
    private String softVersion;

    // 今日使用流量
    private String todayFlow;
    // 本月已耗流量
    private String monthFlow;
    // 累计使用流量
    private String totalFlow;

    // 可选信号
    private String signal;
    // 设备上报限速值
    private String limitSpeed;
    // 信号强度
    private String signalStrength;

    // 最后访问信息
    private String lastAccessInfo;
    // 最后流量上报时间
    private String lastFlowReportTime;
    // 最后上报ICCID
    private String lastReportIccid;
    // 最后使用网络
    private String lastUsedNetwork;
}
