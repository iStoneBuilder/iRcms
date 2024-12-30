package com.stone.it.rcms.mifi.api.vo;

import com.alibaba.fastjson2.JSONObject;
import lombok.Data;

/**
 * 设备控制
 * 
 * @author cj.stone
 * @Date 2024/12/27
 * @Desc
 */
@Data
public class DeviceControlVO {

    // 请求成功标志 true/false
    private boolean success = true;
    // 响应编码
    private String code;
    // 错误信息
    private String message;
    // 控制命令
    private JSONObject data;
}
