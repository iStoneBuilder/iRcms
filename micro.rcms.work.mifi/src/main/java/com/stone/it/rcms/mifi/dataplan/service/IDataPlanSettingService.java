package com.stone.it.rcms.mifi.dataplan.service;

import com.stone.it.rcms.core.annotate.RcmsMethod;
import com.stone.it.rcms.core.vo.PageResult;
import com.stone.it.rcms.core.vo.PageVO;
import com.stone.it.rcms.mifi.dataplan.vo.DataPlanVO;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * 套餐配置管理
 * 
 * @author cj.stone
 * @Date 2024/11/19
 * @Desc
 */
@Path("/data-plan")
@Produces({MediaType.APPLICATION_JSON})
@Consumes(MediaType.APPLICATION_JSON)
@RequiresAuthentication
public interface IDataPlanSettingService {

    @GET
    @Path("/records/page/{curPage}/{pageSize}")
    @RcmsMethod(name = "套餐配置.分页查询")
    @RequiresPermissions("permission:data-plan:page-query")
    PageResult<DataPlanVO> findPageDataPlanResult(@QueryParam("") DataPlanVO vo, @PathParam("") PageVO pageVO);

    @GET
    @Path("/records")
    @RcmsMethod(name = "套餐配置.列表查询")
    @RequiresPermissions("permission:data-plan:list-query")
    List<DataPlanVO> findDataPlanList(@QueryParam("") DataPlanVO vo);

    @GET
    @Path("/records/{device_sn}/optional")
    @RcmsMethod(name = "套餐配置.设备可选套餐查询")
    @RequiresPermissions("permission:data-plan:optional-query")
    List<DataPlanVO> findDeviceOptionalDpList(@PathParam("device_sn") String deviceSn, @QueryParam("") DataPlanVO vo);

    @GET
    @Path("/records/{data_plan_no}")
    @RcmsMethod(name = "套餐配置.详情查询")
    @RequiresPermissions("permission:data-plan:detail")
    DataPlanVO findDataPlanDetail(@PathParam("data_plan_no") String id);

    @POST
    @Path("/records")
    @RcmsMethod(name = "套餐配置.新增")
    @RequiresPermissions("permission:data-plan:create")
    DataPlanVO createDataPlan(DataPlanVO deviceTypeVO);

    @PUT
    @Path("/records/{data_plan_no}")
    @RcmsMethod(name = "套餐配置.更新")
    @RequiresPermissions("permission:data-plan:update")
    int updateDataPlan(@PathParam("data_plan_no") String id, DataPlanVO vo);

    @PUT
    @Path("/records/{data_plan_no}/sell")
    @RcmsMethod(name = "套餐配置.销售配置")
    @RequiresPermissions("permission:data-plan:sell")
    int updateDataPlanSell(@PathParam("data_plan_no") String id, DataPlanVO vo);

    @DELETE
    @Path("/records/{data_plan_no}")
    @RcmsMethod(name = "套餐配置.删除")
    @RequiresPermissions("permission:data-plan:delete")
    int deleteDataPlan(@PathParam("data_plan_no") String id);

}
