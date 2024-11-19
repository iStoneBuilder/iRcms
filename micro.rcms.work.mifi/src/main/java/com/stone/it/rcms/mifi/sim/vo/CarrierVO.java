package com.stone.it.rcms.mifi.sim.vo;

import com.stone.it.rcms.core.vo.BaseVO;
import lombok.Data;

/**
 * 运营商配置
 * 
 * @author cj.stone
 * @Date 2024/11/19
 * @Desc
 */
@Data
public class CarrierVO extends BaseVO {
    // 卡商编码
    private String merchantCode;
    // 运营商编码
    private String carrierCode;
    // 运营商名称
    private String carrierName;
    // 禁用区域
    private String disableArea;
}
