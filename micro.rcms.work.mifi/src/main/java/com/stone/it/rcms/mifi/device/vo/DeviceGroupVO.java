package com.stone.it.rcms.mifi.device.vo;

import com.stone.it.rcms.core.vo.BaseVO;
import lombok.Data;

/**
 * 设备组
 * 
 * @author cj.stone
 * @Date 2024/12/2
 * @Desc
 */
@Data
public class DeviceGroupVO extends BaseVO {

    private String groupId;
    private String groupName;
    private String remark;

}
