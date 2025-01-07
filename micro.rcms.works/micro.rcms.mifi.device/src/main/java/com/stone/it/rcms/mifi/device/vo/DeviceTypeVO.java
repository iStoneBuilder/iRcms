package com.stone.it.rcms.mifi.device.vo;

import com.stone.it.rcms.core.vo.BaseVO;
import lombok.Data;

/**
 * 设备类型
 * 
 * @author cj.stone
 * @Date 2024/12/1
 * @Desc
 */
@Data
public class DeviceTypeVO extends BaseVO {
    private String typeId;
    // 设备类型名称
    private String typeName;
    // 设备类型编码
    private String typeCode;
    // 销售名称
    private String sellName;
    // 设备型号（MIFI）
    private String deviceNo;
    // 本地卡模式
    private String localCardMode;
    // 主图
    private String mainPic;
    // 设备图片
    private String deviPic;
    // 状态
    private String status = "Y";
    // 备注
    private String remark;
}
