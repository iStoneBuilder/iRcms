package com.stone.it.rcms.mifi.sim.vo;

import com.stone.it.rcms.core.vo.BaseVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 运营商配置
 * 
 * @author cj.stone
 * @Date 2024/11/19
 * @Desc
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class CarrierVO extends BaseVO {
    // 卡商编码
    private String merchantCode;
    // 运营商编码
    private String carrierCode;
    // 运营商名称
    private String carrierName;

    private String appKey;
    private String appSecret;
    private String disableArea;
    // 备注
    private String description;
}
