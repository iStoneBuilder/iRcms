package com.stone.it.rcms.mifi.app.vo;

import com.stone.it.rcms.core.vo.BaseVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;

/**
 *
 * @author cj.stone
 * @Date 2024/12/17
 * @Desc
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class MifiDeviceVO extends BaseVO {

    // 当前选中设备
    private String currSelect;
    // 设备SN
    private String deviceSn;
    // 设备主图
    private String deviceImg;
    // 设备套餐数
    private Integer thaliNum;
    // 设备套餐总流量
    private Long thaliTotal;
    // 设备套餐剩余流量
    private Long thaliSurplus;
    // 设备套餐最后过期时间
    private Date thaliExpireTime;
    // 设备实名状态
    private String realNameStatus;
    // SIM卡信息
    private List<MifiDeviceSimVO> simInfo;
    // 设备状态
    private String deviceStatus;
    // 设备SIM卡类型（主卡，副卡）
    private String simType;
    // 设备接入Wi-Fi数量
    private Integer wifiNum;

}
