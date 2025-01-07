package com.stone.it.rcms.mifi.common.vo;

import com.stone.it.rcms.core.vo.BaseVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * @author cj.stone
 * @Date 2025/1/7
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

    private String address;
    private String appKey;
    private String appSecret;
    private String disableArea;
    // 备注
    private String description;
}
