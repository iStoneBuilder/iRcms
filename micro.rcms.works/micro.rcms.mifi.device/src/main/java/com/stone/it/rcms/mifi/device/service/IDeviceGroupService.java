package com.stone.it.rcms.mifi.device.service;

import com.stone.it.rcms.core.annotate.RcmsMethod;
import com.stone.it.rcms.core.vo.PageResult;
import com.stone.it.rcms.core.vo.PageVO;
import com.stone.it.rcms.mifi.device.vo.DeviceGroupVO;
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

import java.util.List;

/**
 * 设备分组
 * 
 * @author cj.stone
 * @Date 2024/11/19
 * @Desc
 */
@Path("/device-group")
@Produces({MediaType.APPLICATION_JSON})
@Consumes(MediaType.APPLICATION_JSON)
@RequiresAuthentication
public interface IDeviceGroupService {

    @GET
    @Path("/records/page/{curPage}/{pageSize}")
    @RcmsMethod(name = "设备分组.分页查询")
    @RequiresPermissions("permission:device-group:page-query")
    PageResult<DeviceGroupVO> findPageDeviceGroupResult(@QueryParam("") DeviceGroupVO groupVO,
        @PathParam("") PageVO pageVO);

    @GET
    @Path("/records")
    @RcmsMethod(name = "设备分组.列表")
    @RequiresPermissions("permission:device-group:list-query")
    List<DeviceGroupVO> findDeviceGroupList(@QueryParam("") DeviceGroupVO groupVO);

    @GET
    @Path("/records/{group_id}")
    @RcmsMethod(name = "设备分组.详情")
    @RequiresPermissions("permission:device-group:detail")
    DeviceGroupVO findDeviceGroupDetail(@PathParam("group_id") String id);

    @POST
    @Path("/records")
    @RcmsMethod(name = "设备分组.新增")
    @RequiresPermissions("permission:device-group:create")
    DeviceGroupVO createDeviceGroup(DeviceGroupVO deviceTypeVO);

    @PUT
    @Path("/records/{group_id}")
    @RcmsMethod(name = "设备分组.更新")
    @RequiresPermissions("permission:device-group:update")
    int updateDeviceGroup(@PathParam("group_id") String id, DeviceGroupVO groupVO);

    @DELETE
    @Path("/records/{group_id}")
    @RcmsMethod(name = "设备分组.删除")
    @RequiresPermissions("permission:device-group:delete")
    int deleteDeviceGroup(@PathParam("group_id") String id);
}
