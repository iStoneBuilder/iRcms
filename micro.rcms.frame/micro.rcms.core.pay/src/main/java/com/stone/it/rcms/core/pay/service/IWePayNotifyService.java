package com.stone.it.rcms.core.pay.service;

import com.stone.it.rcms.core.annotate.RcmsMethod;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * 支付回调接口
 * 
 * @author cj.stone
 * @Date 2024/12/16
 * @Desc
 */
@Produces({MediaType.APPLICATION_JSON})
@Consumes(MediaType.APPLICATION_JSON)
public interface IWePayNotifyService {
    /**
     * 支付回调
     *
     * @param payConfigId 支付配置ID
     * @param request 请求
     * @return 回调结果
     * @throws Exception 异常
     */
    @POST
    @Path("/{pay_config_id}/wx/notify")
    @RcmsMethod(name = "支付.wx订单回调")
    String payNotify(@PathParam("pay_config_id") String payConfigId, HttpServletRequest request) throws Exception;

    /**
     * 退款回调
     *
     * @param payConfigId 支付配置ID
     * @param request 请求
     * @return 回调结果
     * @throws Exception 异常
     */
    @POST
    @Path("/{pay_config_id}/wx/refund/notify")
    @RcmsMethod(name = "支付.wx退款回调")
    String refundNotify(@PathParam("pay_config_id") String payConfigId, HttpServletRequest request) throws Exception;
}
