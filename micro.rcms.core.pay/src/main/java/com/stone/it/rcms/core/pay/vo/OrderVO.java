package com.stone.it.rcms.core.pay.vo;

import com.stone.it.rcms.core.vo.BaseVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 订单信息
 * 
 * @author cj.stone
 * @Date 2024/12/7
 * @Desc
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class OrderVO extends BaseVO {
    // 商品类型 (流量套餐)
    private String productType;
    // 商品名称
    private String productName;
    // 订单号
    private String orderNo;
    // 订单状态 (待支付1、已支付2、已取消3、支付失败4)
    private String orderStatus;
    // 订单金额 (单位：元)
    private String orderAmount;
    // 订单时间
    private Date orderTime;
    // 退款金额 (单位：元)
    private String refundAmount;
    // 退款时间
    private Date refundTime;
    // 退款状态 (申请中、已退款、退款失败)
    private String refundStatus;
    // 支付方式 (微信、支付宝，平台代付)
    private String payType;
    // 支付人 (用户id)
    private String payBy;
    // 支付时间
    private Date payTime;
    // 购买数量
    private Integer buyNum;

    public OrderVO(PayVO payVO, String payWay) {
        this.productType = payVO.getProductType();
        this.productName = payVO.getProductName();
        this.orderNo = payVO.getOrderNo();
        this.payType = payWay;
        this.orderAmount = String.valueOf(payVO.getOrderAmount());
        this.payBy = payVO.getCurrentUserId();
        this.buyNum = payVO.getBuyNum();
    }

    public OrderVO() {}
}
