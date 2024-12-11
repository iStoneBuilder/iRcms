package com.stone.it.rcms.mifi.device.service;

import com.stone.it.rcms.core.annotate.RcmsMethod;
import com.stone.it.rcms.core.vo.PageResult;
import com.stone.it.rcms.core.vo.PageVO;
import com.stone.it.rcms.mifi.device.vo.DeviceDivideVO;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * 设备分发
 * 
 * @author cj.stone
 * @Date 2024/11/19
 * @Desc
 */

@Path("/device-divide")
@Produces({MediaType.APPLICATION_JSON})
@Consumes(MediaType.APPLICATION_JSON)
@RequiresAuthentication
public interface IDeviceDivideService {
    @GET
    @Path("/records/page/{curPage}/{pageSize}")
    @RcmsMethod(name = "设备分发.分页查询")
    @RequiresPermissions("permission:device-divide:page-query")
    PageResult<DeviceDivideVO> findPageDivideResult(@QueryParam("") DeviceDivideVO vo, @PathParam("") PageVO pageVO);

    @POST
    @Path("/records")
    @RcmsMethod(name = "设备分发.分发")
    @RequiresPermissions("permission:device-divide:divide")
    void divideDevice(DeviceDivideVO divideVO);
}
