package com.stone.it.rcms.mifi.api.dao;

import com.stone.it.rcms.mifi.api.vo.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *
 * @author cj.stone
 * @Date 2024/12/27
 * @Desc
 */
public interface IDeviceApiDao {

    // 设备启动查询响应设备信息
    DeviceConfigVO findDeviceInfo(@Param("deviceSn") String deviceSn);

    // 查询设备租户信息
    DeviceDetailVO findDeviceDetail(@Param("deviceSn") String deviceSn);

    // 记录设备上报流量信息
    int createDeviceFlow(@Param("p1") FlowReportVO flowReportVO, @Param("p2") DeviceDetailVO deviceDetailVO);

    // 查询设备租操作指令信息 （未执行）
    List<ControlVO> findDeviceControl(@Param("deviceSn") String deviceSn);

    // 更新设备信息
    int updateDeviceInfoByFlowReport(FlowReportVO reportVO);

    // 查询设备SIM卡信息
    List<SimCardVO> findDeviceSimCardInfo(@Param("deviceSn") String deviceSn);

    int updateDeviceInfoByReport(DeviceReportVO reportVO);

    int createDeviceInfoReport(@Param("p1") DeviceReportVO reportVO, @Param("p2") DeviceDetailVO deviceDetailVO);

    int updateDeviceControl(@Param("operateId") String operateId);

}
