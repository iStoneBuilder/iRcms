package com.stone.it.rcms.mifi.device.vo;

import com.stone.it.rcms.core.vo.BaseVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;

/**
 * 设备分发记录
 * 
 * @author cj.stone
 * @Date 2024/12/11
 * @Desc
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class DeviceDivideVO extends BaseVO {

    // 分发记录ID
    private String divideId;
    // 设备SN
    private String deviceSns;
    // 分发数量
    private Integer divideNum;
    // 设备原始商户
    private String orgMch;
    // 目标商户
    private String targetMch;
    // 分发时间
    private Date divideTime;
    // 分发人
    private String divideUser;
    // 分发状态
    private String divideStatus;
    // 分发备注
    private String remark;

    private List<DeviceVO> list;

}
