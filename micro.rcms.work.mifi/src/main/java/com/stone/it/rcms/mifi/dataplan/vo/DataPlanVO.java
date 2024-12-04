package com.stone.it.rcms.mifi.dataplan.vo;

import com.stone.it.rcms.core.vo.BaseVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * 流量套餐
 * 
 * @author cj.stone
 * @Date 2024/12/3
 * @Desc
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class DataPlanVO extends BaseVO {
    // 套餐编号
    private String dataPlanNo;
    // 套餐名称
    private String dataPlanName;
    // 套餐图片
    private String dataPlanPic;
    // 套餐类型(国内套餐domestic/international国际套餐)
    private String dataPlanType;
    // 套餐成本
    private BigDecimal dataPlanCost;
    // 套餐价格
    private BigDecimal dataPlanPrice;
    // 套餐流量
    private BigDecimal dataPlanFlow;
    // 套餐虚量
    private BigDecimal dataPlanVoidFlow;
    // 计费类型
    private String chargeType;
    // 有效期
    private String validDuration;
    // 限速
    private String limitSpeed;
    // 赠送月份
    private String giftDuration;
    // 是否上架
    private String isSale;
    // 限制购买次数
    private String limitNo;
    // 是否赠送
    private String isGift;
    // 是否推荐
    private String isRecommend;
    // 套餐组
    private String dataPlanGroup;
    // 套餐规则
    private String dataPlanRules;
    // 排序
    private String sort;
    // 销售设备类型
    private String saleDeviceType;
    // 销售设备组
    private String saleDeviceGroup;
}
