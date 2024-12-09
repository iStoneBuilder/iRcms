package com.stone.it.rcms.core.pay.vo;

import com.stone.it.rcms.core.vo.BaseVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * @author cj.stone
 * @Date 2024/12/7
 * @Desc
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class PayVO extends BaseVO {
    // 设备SN
    private String deviceSn;
    // 商品id
    private String productId;
    // 商品类型 (流量套餐)
    private String productType;
    // 商品名称
    private String productName;
    // 订单号
    private String orderNo;
    // 订单金额 (单位：元)
    private Long orderAmount;
    // 退款金额 (单位：元)
    private Long refundAmount;
    // 支付人 (用户id)
    private String openId;
    // 购买数量
    private Integer buyNum;
}
