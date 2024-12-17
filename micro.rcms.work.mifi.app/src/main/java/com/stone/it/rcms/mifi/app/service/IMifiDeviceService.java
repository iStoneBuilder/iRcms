package com.stone.it.rcms.mifi.app.service;

import com.stone.it.rcms.core.annotate.RcmsMethod;
import com.stone.it.rcms.mifi.app.vo.MifiDeviceVO;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * 设备相关接口
 * 
 * @author cj.stone
 * @Date 2024/12/17
 * @Desc
 */
@Path("/device")
@Produces({MediaType.APPLICATION_JSON})
@Consumes(MediaType.APPLICATION_JSON)
@RequiresAuthentication
public interface IMifiDeviceService {

    /**
     * 查询用户设备列表
     * 
     * @param deviceVO deviceVO
     * @return list
     */
    @GET
    @Path("/list")
    @RcmsMethod(name = "用户设备.列表查询", type = "Y")
    @RequiresPermissions("permission:user-device:list-query")
    List<MifiDeviceVO> findUserDevices(@QueryParam("") MifiDeviceVO deviceVO);

    /**
     * 查询设备详情
     *
     * @param deviceSn deviceSn
     * @param deviceVO deviceVO
     * @return MifiDeviceVO
     */
    @GET
    @Path("/{device_sn}")
    @RcmsMethod(name = "用户设备.详情查询", type = "Y")
    @RequiresPermissions("permission:user-device:detail-query")
    MifiDeviceVO findDeviceDetailBySn(@PathParam("device_sn") String deviceSn, @QueryParam("") MifiDeviceVO deviceVO);

}
