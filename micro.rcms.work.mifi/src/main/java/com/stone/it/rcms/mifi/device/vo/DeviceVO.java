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
    // 贴片卡ICCID
    private String iccid;
    // 贴片卡2ICCID
    private String iccid2;
    // 贴片卡物联网号
    private String iotNo;
    // 贴片卡物联网号2
    private String iotNo2;
}
