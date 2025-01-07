package com.stone.it.rcms.mifi.device.service;

import com.stone.it.rcms.core.annotate.RcmsMethod;
import com.stone.it.rcms.core.vo.PageResult;
import com.stone.it.rcms.core.vo.PageVO;
import com.stone.it.rcms.mifi.device.vo.DeviceNameVO;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * 设备实名
 * 
 * @author cj.stone
 * @Date 2024/11/19
 * @Desc
 */
@Path("/device-name")
@Produces({MediaType.APPLICATION_JSON})
@Consumes(MediaType.APPLICATION_JSON)
@RequiresAuthentication
public interface IDeviceRealNameService {

    @GET
    @Path("/records/page/{curPage}/{pageSize}")
    @RcmsMethod(name = "设备实名.分页查询")
    @RequiresPermissions("permission:device-name:page:query")
    PageResult<DeviceNameVO> findSimNamePageResult(@QueryParam("") DeviceNameVO deviceNameVO,
        @PathParam("") PageVO pageVO);

    @POST
    @Path("/records")
    @RcmsMethod(name = "设备实名.创建", type = "Y")
    @RequiresPermissions("permission:device-name:create")
    int createSimName(DeviceNameVO deviceNameVO);

    @PUT
    @Path("/records/{iccid}/sync")
    @RcmsMethod(name = "设备实名.同步")
    @RequiresPermissions("permission:device-name:sync")
    int syncSimName(@PathParam("iccid") String iccid, DeviceNameVO deviceNameVO);

    @DELETE
    @Path("/records/{iccid}/clean")
    @RcmsMethod(name = "设备实名.清理")
    @RequiresPermissions("permission:device-name:clean")
    int cleanSimName(@PathParam("iccid") String iccid, DeviceNameVO deviceNameVO);
}
