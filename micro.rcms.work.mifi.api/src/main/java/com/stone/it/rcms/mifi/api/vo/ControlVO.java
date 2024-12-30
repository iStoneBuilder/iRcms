package com.stone.it.rcms.mifi.api.vo;

import lombok.Data;

/**
 * 控制VO
 * 
 * @author cj.stone
 * @Date 2024/12/27
 * @Desc
 */
@Data
public class ControlVO {

    // 操作ID
    private String operateId;
    // 指令编码：
    // 100：双网切换
    // 101：WiFi信息修改
    // 102：关机
    // 103：重启
    // 104：恢复出厂设置
    // 105：清除设备缓存
    private int cmdId;
    private String param;

    // 100 双网切换 (1:电信 2:联通)
    private int isp;
    // 110 WiFi信息修改
    private String wifiName;
    private String wifiPwd;

}
