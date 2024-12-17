package com.stone.it.rcms.mifi.app.vo;

import com.stone.it.rcms.core.vo.BaseVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 设备SIM信息
 * 
 * @author cj.stone
 * @Date 2024/12/17
 * @Desc
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class MifiDeviceSimVO extends BaseVO {

    // 运营商
    private String carrier;
    // 运营商名称
    private String carrierName;

    // 卡号
    private String iccid;
    // 实名状态
    private String realNameStatus;

}
