package com.stone.it.rcms.core.pay.service;

import com.stone.it.rcms.core.annotate.RcmsMethod;
import com.stone.it.rcms.core.pay.vo.PayConfigVO;
import com.stone.it.rcms.core.vo.PageResult;
import com.stone.it.rcms.core.vo.PageVO;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * 支付配置服务
 * 
 * @author cj.stone
 * @Date 2024/12/9
 * @Desc
 */
@Path("/pay-config")
@Produces({MediaType.APPLICATION_JSON})
@Consumes(MediaType.APPLICATION_JSON)
@RequiresAuthentication
public interface IPayConfigService {

    @GET
    @Path("/records/page/{curPage}/{pageSize}")
    @RcmsMethod(name = "支付配置.分页查询")
    @RequiresPermissions("permission:pay-config:page-query")
    PageResult<PayConfigVO> findPayConfigPageList(@QueryParam("") PayConfigVO payConfigVO,
        @PathParam("") PageVO pageVO);

    @GET
    @Path("/{pai_config_id}")
    @RcmsMethod(name = "支付配置.详情查询")
    @RequiresPermissions("permission:pay-config:detail-query")
    PayConfigVO findPayConfigDetail(@PathParam("pai_config_id") String payConfigId,
        @QueryParam("") PayConfigVO payConfigVO);

    @POST
    @Path("/records")
    @RcmsMethod(name = "支付配置.新增")
    @RequiresPermissions("permission:pay-config:create")
    PayConfigVO createPayConfig(PayConfigVO payConfigVO);

    @PUT
    @Path("/records/{pai_config_id}")
    @RcmsMethod(name = "支付配置.修改")
    @RequiresPermissions("permission:pay-config:update")
    PayConfigVO updatePayConfig(@PathParam("pai_config_id") String payConfigId, PayConfigVO payConfigVO);

    @DELETE
    @Path("/records/{pai_config_id}")
    @RcmsMethod(name = "支付配置.删除")
    @RequiresPermissions("permission:pay-config:delete")
    void deletePayConfig(@PathParam("pai_config_id") String payConfigId);

}
