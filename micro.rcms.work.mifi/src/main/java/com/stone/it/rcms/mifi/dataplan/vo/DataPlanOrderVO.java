package com.stone.it.rcms.mifi.dataplan.vo;

import com.stone.it.rcms.core.vo.BaseVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 套餐订单
 * 
 * @author cj.stone
 * @Date 2024/11/19
 * @Desc
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class DataPlanOrderVO extends BaseVO {

    /** 套餐订单ID */
    private String dpOrderId;
    /** 订单号 */
    private String orderNo;
    /** 套餐编号 */
    private String dataPlanNo;

    /** 设备SN */
    private String deviceSn;
    /** 生效时间 */
    private Date effectiveTime;
    /** 失效时间 */
    private Date expireTime;

    /** 套餐总量（MB） */
    private Long totalFlow;
    /** 套餐剩余量（MB） */
    private Long remainFlow;
    /** 当前已用量（MB） */
    private Long usedFlow;

    /** 是否限速 */
    private String isLimitSpeed;
    /** 限速值（KB/S） */
    private Long limitSpeed;
    /** 是否可用 */
    private String isAvailable;

}
