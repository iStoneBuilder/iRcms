package com.stone.it.rcms.mifi.api.service.impl;

import com.alibaba.fastjson2.JSONObject;
import com.stone.it.rcms.mifi.api.dao.IDeviceApiDao;
import com.stone.it.rcms.mifi.api.service.IDeviceApiService;
import com.stone.it.rcms.mifi.api.vo.*;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

/**
 * 设备与平台交互
 * 
 * @author cj.stone
 * @Date 2024/12/27
 * @Desc
 */
@Named
public class DeviceApiService implements IDeviceApiService {

    @Inject
    private IDeviceApiDao deviceApiDao;

    @Override
    public DeviceConfigVO getDeviceInfo(String deviceSn) {
        DeviceConfigVO deviceConfigVO = deviceApiDao.findDeviceInfo(deviceSn);
        List<SimCardVO> list = deviceApiDao.findDeviceSimCardInfo(deviceSn);
        deviceConfigVO.setSeedCard(list);
        return deviceConfigVO;
    }

    @Override
    public DeviceControlVO deviceFlowReport(String deviceSn, FlowReportVO reportVO) {
        reportVO.setDeviceSn(deviceSn);
        // 查询设备信息
        DeviceDetailVO detailVO = deviceApiDao.findDeviceDetail(deviceSn);
        // 创建流量上报记录
        deviceApiDao.createDeviceFlow(reportVO, detailVO);
        // 更新设备信息
        deviceApiDao.updateDeviceInfoByFlowReport(reportVO);
        // 查询是否存在设备控制命令
        return getDeviceControlVO(deviceSn);
    }

    @Override
    public DeviceControlVO deviceInfoReport(String deviceSn, DeviceReportVO reportVO) {
        reportVO.setDeviceSn(deviceSn);
        reportVO.setReportStr(reportVO.toString());
        // 查询设备信息
        DeviceDetailVO detailVO = deviceApiDao.findDeviceDetail(deviceSn);
        // 创建上报记录
        deviceApiDao.createDeviceInfoReport(reportVO, detailVO);
        // 更新设备信息
        deviceApiDao.updateDeviceInfoByReport(reportVO);
        // 判断是否有存在控制命令下发
        if (reportVO.getOperateId() != null && !reportVO.getOperateId().isEmpty()) {
            // 更新控制记录
            deviceApiDao.updateDeviceControl(reportVO.getOperateId());
        }
        // 返回控制信息
        return getDeviceControlVO(deviceSn);
    }

    private DeviceControlVO getDeviceControlVO(String deviceSn) {
        DeviceControlVO controlVO = new DeviceControlVO();
        List<ControlVO> controls = deviceApiDao.findDeviceControl(deviceSn);
        if (!controls.isEmpty()) {
            ControlVO control = controls.get(0);
            JSONObject data = new JSONObject();
            data.put("operateId", control.getOperateId());
            data.put("cmdId", control.getCmdId());
            if (control.getCmdId() == 101) {
                String[] namePwd = control.getParam().split(",");
                data.put("wifiName", namePwd[0]);
                data.put("wifiPwd", namePwd[1]);
            } else if (control.getCmdId() == 100) {
                data.put("isp", control.getParam());
            }
            controlVO.setData(data);
        }
        return controlVO;
    }
}
