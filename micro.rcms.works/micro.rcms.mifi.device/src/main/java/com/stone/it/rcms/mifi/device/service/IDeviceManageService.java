package com.stone.it.rcms.mifi.device.service;

import com.stone.it.rcms.core.annotate.RcmsMethod;
import com.stone.it.rcms.core.vo.PageResult;
import com.stone.it.rcms.core.vo.PageVO;
import com.stone.it.rcms.mifi.device.vo.DeviceVO;
import com.stone.it.rcms.tcp.netty4.vo.DeviceSetupVO;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * 设备管理
 * 
 * @author cj.stone
 * @Date 2024/11/19
 * @Desc
 */
@Path("/device-manage")
@Produces({MediaType.APPLICATION_JSON})
@Consumes(MediaType.APPLICATION_JSON)
@RequiresAuthentication
public interface IDeviceManageService {

    @GET
    @Path("/records/page/{curPage}/{pageSize}")
    @RcmsMethod(name = "终端设备.分页查询")
    @RequiresPermissions("permission:device-manage:page-query")
    PageResult<DeviceVO> findPageDeviceResult(@QueryParam("") DeviceVO vo, @PathParam("") PageVO pageVO);

    @GET
    @Path("/records/{device_sn}")
    @RcmsMethod(name = "终端设备.详情")
    @RequiresPermissions("permission:device-manage:detail")
    DeviceVO findDeviceDetail(@PathParam("device_sn") String deviceSn);

    @POST
    @Path("/records")
    @RcmsMethod(name = "终端设备.入库")
    @RequiresPermissions("permission:device-manage:create")
    int createDevice(List<DeviceVO> list);

    @PUT
    @Path("/records/group")
    @RcmsMethod(name = "终端设备.分组")
    @RequiresPermissions("permission:device-manage:group")
    int setDeviceGroup(List<DeviceVO> list);

    @PUT
    @Path("/records/{device_sn}")
    @RcmsMethod(name = "终端设备.更新")
    @RequiresPermissions("permission:device-manage:update")
    int updateDevice(@PathParam("device_sn") String deviceSn, DeviceVO vo);

    @DELETE
    @Path("/records/{device_sn}")
    @RcmsMethod(name = "终端设备.删除")
    @RequiresPermissions("permission:device-manage:delete")
    int deleteDevice(@PathParam("device_sn") String deviceSn);

    @POST
    @Path("/records/{device_sn}/{cmd_id}")
    @RcmsMethod(name = "终端设备.控制")
    @RequiresPermissions("permission:device-manage:control")
    int controlDevice(@PathParam("device_sn") String deviceSn, DeviceSetupVO vo);
}
