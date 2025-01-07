package com.stone.it.rcms.mifi.app.service;

import com.stone.it.rcms.core.annotate.RcmsMethod;
import com.stone.it.rcms.mifi.app.vo.MifiCheckVO;
import com.stone.it.rcms.mifi.app.vo.MifiDeviceVO;
import com.stone.it.rcms.mifi.app.vo.MifiIccidVO;
import com.stone.it.rcms.mifi.app.vo.MifiWifiVO;
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
     * 查询设备详情
     *
     * @param deviceVO deviceVO
     * @return MifiDeviceVO
     */
    @GET
    @Path("/used")
    @RcmsMethod(name = "用户设备.当前使用", type = "Y")
    @RequiresPermissions("permission:user-device:detail-query")
    MifiDeviceVO findUserUsedDevice(@QueryParam("") MifiDeviceVO deviceVO);

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
     * 切换当前设备
     *
     * @param deviceSn deviceSn
     * @param deviceVO deviceVO
     * @return MifiDeviceVO
     */
    @POST
    @Path("/{device_sn}/change")
    @RcmsMethod(name = "用户设备.切换设备", type = "Y")
    @RequiresPermissions("permission:user-device:device-change")
    int changeCurrentDevice(@PathParam("device_sn") String deviceSn, MifiDeviceVO deviceVO);

    @POST
    @Path("/{device_sn}/change/wifi")
    @RcmsMethod(name = "用户设备.wifi管理", type = "Y")
    @RequiresPermissions("permission:user-device:change-wifi")
    int changeCurrentDeviceWifi(@PathParam("device_sn") String deviceSn, MifiWifiVO wifiVO);

    @POST
    @Path("/{device_sn}/change/iccid")
    @RcmsMethod(name = "用户设备.iccid管理", type = "Y")
    @RequiresPermissions("permission:user-device:change-iccid")
    int changeCurrentDeviceIccid(@PathParam("device_sn") String deviceSn, MifiIccidVO iccidVO);

    @POST
    @Path("/{device_sn}/check")
    @RcmsMethod(name = "用户设备.设备检测", type = "Y")
    @RequiresPermissions("permission:user-device:device-check")
    List<MifiCheckVO> checkCurrentDevice(@PathParam("device_sn") String deviceSn, MifiDeviceVO deviceVO);
}
