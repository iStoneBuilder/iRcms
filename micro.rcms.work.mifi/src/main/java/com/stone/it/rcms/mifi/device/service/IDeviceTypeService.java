package com.stone.it.rcms.mifi.device.service;

import com.stone.it.rcms.core.annotate.RcmsMethod;
import com.stone.it.rcms.core.vo.PageResult;
import com.stone.it.rcms.core.vo.PageVO;
import com.stone.it.rcms.mifi.device.vo.DeviceTypeVO;
import java.util.List;
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
 * 设备类型
 * 
 * @author cj.stone
 * @Date 2024/11/19
 * @Desc
 */
@Path("/device-type")
@Produces({MediaType.APPLICATION_JSON})
@Consumes(MediaType.APPLICATION_JSON)
@RequiresAuthentication
public interface IDeviceTypeService {

    @GET
    @Path("/records/page/{curPage}/{pageSize}")
    @RcmsMethod(name = "设备类型.分页查询")
    @RequiresPermissions("permission:device-type:page-query")
    PageResult<DeviceTypeVO> findPageDeviceTypeResult(@QueryParam("") DeviceTypeVO deviceTypeVO,
        @PathParam("") PageVO pageVO);

    @GET
    @Path("/records")
    @RcmsMethod(name = "设备类型.列表查询")
    @RequiresPermissions("permission:device-type:list-query")
    List<DeviceTypeVO> findDeviceTypeList(@QueryParam("") DeviceTypeVO deviceTypeVO);

    @GET
    @Path("/records/{device_type_id}")
    @RcmsMethod(name = "设备类型.详情")
    @RequiresPermissions("permission:device-type:detail")
    DeviceTypeVO findDeviceTypeDetail(@PathParam("device_type_id") String id);

    @POST
    @Path("/records")
    @RcmsMethod(name = "设备类型.新增")
    @RequiresPermissions("permission:device-type:create")
    DeviceTypeVO createDeviceType(DeviceTypeVO deviceTypeVO);

    @PUT
    @Path("/records/{device_type_id}")
    @RcmsMethod(name = "设备类型.更新")
    @RequiresPermissions("permission:device-type:update")
    int updateDeviceType(@PathParam("device_type_id") String id, DeviceTypeVO deviceTypeVO);

    @DELETE
    @Path("/records/{device_type_id}")
    @RcmsMethod(name = "设备类型.删除")
    @RequiresPermissions("permission:device-type:delete")
    int deleteDeviceType(@PathParam("device_type_id") String id);

}
