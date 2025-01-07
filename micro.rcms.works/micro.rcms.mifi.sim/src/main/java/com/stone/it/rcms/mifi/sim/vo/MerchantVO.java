package com.stone.it.rcms.mifi.sim.vo;

import com.stone.it.rcms.core.vo.BaseVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 卡商
 * 
 * @author cj.stone
 * @Date 2024/11/19
 * @Desc
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class MerchantVO extends BaseVO {
    // 卡商编码
    private String merchantCode;
    // 卡商名称
    private String merchantName;
    // 禁用区域
    private String disableArea;
    // 备注
    private String description;
}
