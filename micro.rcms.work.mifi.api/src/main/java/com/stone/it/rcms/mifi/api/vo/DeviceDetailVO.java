package com.stone.it.rcms.mifi.api.vo;

import lombok.Data;

/**
 * 设备详细信息
 * 
 * @author cj.stone
 * @Date 2024/12/27
 * @Desc
 */
@Data
public class DeviceDetailVO {
    private String tenantId;
    private String enterpriseId;
    private String deviceSn;
}
