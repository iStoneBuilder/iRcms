package com.stone.it.rcms.mifi.sim.service;

import com.stone.it.rcms.core.annotate.RcmsMethod;
import com.stone.it.rcms.core.vo.PageResult;
import com.stone.it.rcms.core.vo.PageVO;
import com.stone.it.rcms.mifi.sim.vo.CarrierVO;
import com.stone.it.rcms.mifi.sim.vo.MerchantVO;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;

/**
 * 卡商管理
 * 
 * @author cj.stone
 * @Date 2024/11/19
 * @Desc
 */

@Path("/merchant")
@Produces({MediaType.APPLICATION_JSON})
@Consumes(MediaType.APPLICATION_JSON)
@RequiresAuthentication
public interface IMerchantService {

    @GET
    @Path("/records/page/{curPage}/{pageSize}")
    @RcmsMethod(name = "卡商管理.分页查询")
    @RequiresPermissions("permission:merchant:page:query")
    PageResult<MerchantVO> findMerchantPageResult(@QueryParam("") MerchantVO merchantVO, @PathParam("") PageVO pageVO);

    @POST
    @Path("/records")
    @RcmsMethod(name = "卡商管理.新增")
    @RequiresPermissions("permission:merchant:create")
    int createMerchant(MerchantVO merchantVO);

    @PUT
    @Path("/records/{merchant_code}")
    @RcmsMethod(name = "卡商管理.更新")
    @RequiresPermissions("permission:merchant:update")
    int updateMerchant(@PathParam("merchant_code") String code, MerchantVO merchantVO);

    @DELETE
    @Path("/records/{merchant_code}")
    @RcmsMethod(name = "卡商管理.删除")
    @RequiresPermissions("permission:merchant:delete")
    int deleteMerchant(@PathParam("merchant_code") String code);

    @GET
    @Path("/carrier/records/page/{curPage}/{pageSize}")
    @RcmsMethod(name = "卡商管理.运营商-分页查询")
    @RequiresPermissions("permission:merchant:page:query")
    PageResult<CarrierVO> findMerchantCarrierPageResult(@QueryParam("") CarrierVO carrierVO,
        @PathParam("") PageVO pageVO);

    @POST
    @Path("/carrier/records")
    @RcmsMethod(name = "卡商管理.运营商-新增")
    @RequiresPermissions("permission:merchant:carrier:create")
    int createMerchantCarrier(CarrierVO carrierVO);

    @PUT
    @Path("/carrier/records/{merchant_code}/{carrier_code}")
    @RcmsMethod(name = "卡商管理.运营商-更新")
    @RequiresPermissions("permission:merchant:carrier:update")
    int updateMerchantCarrier(@PathParam("merchant_code") String merchantCode,
        @PathParam("carrier_code") String carrierCode, CarrierVO carrierVO);

    @DELETE
    @Path("/carrier/records/{merchant_code}/{carrier_code}")
    @RcmsMethod(name = "卡商管理.运营商-删除")
    @RequiresPermissions("permission:merchant:carrier:delete")
    int deleteMerchantCarrier(@PathParam("merchant_code") String merchantCode,
        @PathParam("carrier_code") String carrierCode);

}
