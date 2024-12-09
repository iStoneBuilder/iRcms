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
    // 商品ID
    private String productId;
    // 商品类型 (流量套餐)
    private String productType;
    // 商品名称
    private String productName;
    // 设备SN
    private String deviceSn;
    // 订单号
    private String orderNo;
    // 订单状态 (待支付1、已支付2、已取消3、支付失败4)
    private String orderStatus;
    // 订单金额 (单位：元)
    private String orderAmount;
    // 订单时间
    private Date orderTime;
    // 退款方式（全额退款，按用量计算，自定义）
    private String refundWay;
    // 退款金额 (单位：元)
    private String refundAmount;
    // 退款时间
    private Date refundTime;
    // 退款状态 (申请中、已退款、退款失败)
    private String refundStatus;
    // 支付方式 (微信、支付宝，平台代付)
    private String payType;
    // JSAPI支付‌：适用于微信内部的网页支付，如公众号文章、小程序页面等。
    // APP支付‌：适用于移动应用中的支付场景，提供更流畅的用户体验。
    // H5支付‌：适用于所有支持HTML5的浏览器，方便从外部网站唤起微信支付。
    // Native支付‌：适用于需要扫码支付的场景，如PC网站、实体店等。
    // ‌小程序支付‌：专门为微信小程序设计，提供便捷的支付方式。
    // 支付来源 (APP、PC、小程序)
    private String paySource;
    // 支付人 (用户id)
    private String payBy;
    // 支付时间
    private Date payTime;
    // 购买数量
    private Integer buyNum;
    // 微信支付系统生成的订单号
    private String transactionId;

    public OrderVO(PayVO payVO, String payWay, String paySource) {
        this.productType = payVO.getProductType();
        this.productName = payVO.getProductName();
        this.orderNo = payVO.getOrderNo();
        this.payType = payWay;
        this.paySource = paySource;
        this.orderAmount = String.valueOf(payVO.getOrderAmount());
        this.payBy = payVO.getCurrentUserId();
        this.buyNum = payVO.getBuyNum();
    }

    public OrderVO() {}
}
