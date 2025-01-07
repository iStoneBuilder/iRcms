package com.stone.it.rcms.mifi.api.service;

import com.stone.it.rcms.mifi.api.vo.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * 设备交互接口
 * 
 * @author cj.stone
 * @Date 2024/12/27
 * @Desc
 */
@Path("/v1")
@Produces({MediaType.APPLICATION_JSON})
@Consumes(MediaType.APPLICATION_JSON)
public interface IDeviceApiService {

    /**
     * 获取设备信息
     */
    @POST
    @Path("/{device_sn}/config")
    DeviceConfigVO getDeviceInfo(@PathParam("device_sn") String deviceSn);

    /**
     * 上报流量信息
     */
    @POST
    @Path("/{device_sn}/flow/report")
    DeviceControlVO deviceFlowReport(@PathParam("device_sn") String deviceSn, FlowReportVO reportVO);

    /**
     * 上报设备信息
     */
    @POST
    @Path("/{device_sn}/info/report")
    DeviceControlVO deviceInfoReport(@PathParam("device_sn") String deviceSn, DeviceReportVO deviceInfoVO);

}
