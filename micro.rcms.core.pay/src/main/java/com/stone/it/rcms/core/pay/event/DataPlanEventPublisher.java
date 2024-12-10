package com.stone.it.rcms.core.pay.event;

import com.alibaba.fastjson2.JSONObject;
import com.stone.it.rcms.core.common.vo.DpEventVO;
import com.stone.it.rcms.core.pay.vo.OrderVO;
import com.wechat.pay.java.service.refund.model.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 流量套餐推送事件
 * 
 * @author cj.stone
 * @Date 2024/12/10
 * @Desc
 */
@Service
@RequiredArgsConstructor
public class DataPlanEventPublisher {

    private final ApplicationContext applicationContext;

    public void buyDdp(OrderVO orderVO) {
        JSONObject dpOrderVO = new JSONObject();
        // 设置设备sn
        dpOrderVO.put("deviceSn", orderVO.getDeviceSn());
        // 设置用户id
        dpOrderVO.put("createBy", orderVO.getPayBy());
        // 设置套餐编号
        dpOrderVO.put("dataPlanNo", orderVO.getProductId());
        // 设置商户id
        dpOrderVO.put("enterpriseId", orderVO.getEnterpriseId());
        // 设置租户id
        dpOrderVO.put("tenantId", orderVO.getTenantId());
        // 设置订单编号
        dpOrderVO.put("orderNo", orderVO.getOrderNo());
        // 设置套餐付款时间
        dpOrderVO.put("effectiveTime", new Date());
        String dbp = JSONObject.toJSONString(dpOrderVO);
        DpEventVO event = new DpEventVO("create", dbp);
        event.setEventContext(dbp);
        applicationContext.publishEvent(event);
    }

    public void refund(String outTradeNo) {
        JSONObject dpOrderVO = new JSONObject();
        dpOrderVO.put("orderNo", outTradeNo);
        dpOrderVO.put("isAvailable", "N");
        String dbp = JSONObject.toJSONString(dpOrderVO);
        DpEventVO event = new DpEventVO("refund", dbp);
        event.setEventContext(dbp);
        applicationContext.publishEvent(event);
    }
}
