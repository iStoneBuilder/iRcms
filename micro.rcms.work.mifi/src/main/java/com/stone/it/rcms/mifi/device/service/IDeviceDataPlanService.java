package com.stone.it.rcms.mifi.device.service;

import com.stone.it.rcms.core.annotate.RcmsMethod;
import com.stone.it.rcms.core.common.vo.DpEventVO;
import com.stone.it.rcms.core.vo.PageResult;
import com.stone.it.rcms.core.vo.PageVO;
import com.stone.it.rcms.mifi.device.vo.DeviceDpVO;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * 设备套餐
 * 
 * @author cj.stone
 * @Date 2024/12/10
 * @Desc
 */

@Path("/device-data-plan")
@Produces({MediaType.APPLICATION_JSON})
@Consumes(MediaType.APPLICATION_JSON)
@RequiresAuthentication
public interface IDeviceDataPlanService {

    @GET
    @Path("/records/page/{curPage}/{pageSize}")
    @RcmsMethod(name = "设备套餐.分页查询")
    @RequiresPermissions("permission:device-data-plan:page-query")
    PageResult<DeviceDpVO> findPageDeviceDpResult(@QueryParam("") DeviceDpVO vo, @PathParam("") PageVO pageVO);

    @GET
    @Path("/records/{data_plan_id}")
    @RcmsMethod(name = "设备套餐.套餐详情查询")
    @RequiresPermissions("permission:device-data-plan:detail")
    DeviceDpVO findDeviceDpDetail(@PathParam("data_plan_id") String id, @QueryParam("") DeviceDpVO vo);

    @GET
    @Path("/records/{device_sn}/device-dp")
    @RcmsMethod(name = "设备套餐.设备套餐查询")
    @RequiresPermissions("permission:device-data-plan:list-query")
    List<DeviceDpVO> findDeviceDpList(@PathParam("device_sn") String deviceSn, @QueryParam("") DeviceDpVO vo);

    int createDeviceDp(DpEventVO eventVO);

}
