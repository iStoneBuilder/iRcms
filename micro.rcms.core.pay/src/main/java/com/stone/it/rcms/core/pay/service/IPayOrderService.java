package com.stone.it.rcms.core.pay.service;

import com.stone.it.rcms.core.annotate.RcmsMethod;
import com.stone.it.rcms.core.pay.vo.OrderVO;
import com.stone.it.rcms.core.vo.PageResult;
import com.stone.it.rcms.core.vo.PageVO;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * 订单服务
 * 
 * @author cj.stone
 * @Date 2024/12/7
 * @Desc
 */

@Path("/order")
@Produces({MediaType.APPLICATION_JSON})
@Consumes(MediaType.APPLICATION_JSON)
@RequiresAuthentication
public interface IPayOrderService {

    @GET
    @Path("/records/page/{curPage}/{pageSize}")
    @RcmsMethod(name = "订单管理.分页查询")
    @RequiresPermissions("permission:order:page-query")
    PageResult<OrderVO> findOrderPageResult(@QueryParam("") OrderVO orderVO, @PathParam("") PageVO pageVO);

    @GET
    @Path("/records/{order_no}")
    @RcmsMethod(name = "订单管理.订单详情")
    @RequiresPermissions("permission:order:detail")
    OrderVO findOrderDetail(@PathParam("order_no") String orderNo, @QueryParam("") OrderVO orderVO);

    @POST
    @Path("/records/{order_no}/refund")
    @RcmsMethod(name = "订单管理.退款")
    @RequiresPermissions("permission:order:refund")
    String refundOrder(@PathParam("order_no") String orderNo, @QueryParam("") OrderVO orderVO) throws Exception;

}
