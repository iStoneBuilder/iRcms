package com.stone.it.rcms.core.pay.service;

import com.alibaba.fastjson2.JSONObject;
import com.stone.it.rcms.core.annotate.RcmsMethod;
import com.stone.it.rcms.core.pay.vo.PayVO;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;

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

    @POST
    @Path("/{pay_way}/order")
    @RcmsMethod(name = "支付.创建订单")
    @RequiresPermissions("permission:pay:create")
    JSONObject pay(@PathParam("pay_way") String payWay, PayVO payVO);

    @POST
    @Path("/wx/notify")
    @RcmsMethod(name = "支付.wx订单回调")
    String payNotify(HttpServletRequest request) throws Exception;

    @POST
    @Path("/wx/refund")
    @RcmsMethod(name = "支付.wx申请退款")
    String refund(PayVO payVO) throws Exception;

    @POST
    @Path("/wx/refund/notify")
    @RcmsMethod(name = "支付.wx退款回调")
    String refundNotify(HttpServletRequest request) throws Exception;

}
