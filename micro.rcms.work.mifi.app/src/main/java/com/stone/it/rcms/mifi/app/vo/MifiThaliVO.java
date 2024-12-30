package com.stone.it.rcms.mifi.app.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.stone.it.rcms.core.vo.BaseVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 套餐信息
 * 
 * @author cj.stone
 * @Date 2024/12/17
 * @Desc
 */
@EqualsAndHashCode(callSuper = true)
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MifiThaliVO extends BaseVO {
    private String dpId;
    // 套餐名称
    private String thaliName;
    // 套餐流量
    private Long totalFlow;
    // 已使用流量
    private Long usedFlow;
    // 套餐剩余量
    private Long remainFlow;
    // 当前总共已用量
    private Long totalUsedFlow;
    // 生效时间
    private Date effectiveTime;
    // 失效时间
    private Date expireTime;
    // 计费类型
    private String chargeType;
    // 有效时长
    private Long validDuration;
}
