package com.stone.it.rcms.core.pay.service;

import com.alibaba.fastjson2.JSONObject;
import com.stone.it.rcms.core.annotate.RcmsMethod;
import com.stone.it.rcms.core.pay.vo.PayVO;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * 支付模块
 * 
 * @author cj.stone
 * @Date 2024/12/7
 * @Desc
 */
@Path("/pay")
@Produces({MediaType.APPLICATION_JSON})
@Consumes(MediaType.APPLICATION_JSON)
@RequiresAuthentication
public interface IPayService {

    /**
     * 创建订单
     * 
     * @param payWay 支付方式
     * @param paySource 支付来源
     * @param payVO 订单信息
     * @return 订单信息
     */
    @POST
    @Path("/{pay_way}/{pay_source}/order")
    @RcmsMethod(name = "支付.创建订单")
    @RequiresPermissions("permission:pay:create")
    JSONObject pay(@PathParam("pay_way") String payWay, @PathParam("pay_source") String paySource, PayVO payVO);

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
     * 退款
     *
     * @param payVO 订单信息
     * @return 退款结果
     * @throws Exception 异常
     */
    String refund(PayVO payVO) throws Exception;

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
