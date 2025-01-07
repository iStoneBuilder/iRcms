package com.stone.it.rcms.mifi.device.service.impl;

import com.stone.it.rcms.core.common.service.ICommonService;
import com.stone.it.rcms.core.common.vo.CommonVO;
import com.stone.it.rcms.core.vo.PageResult;
import com.stone.it.rcms.core.vo.PageVO;
import com.stone.it.rcms.mifi.device.vo.DeviceDpVO;
import com.stone.it.rcms.mifi.device.dao.IDeviceDataPlanDao;
import com.stone.it.rcms.mifi.device.service.IDeviceDataPlanService;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author cj.stone
 * @Date 2024/12/10
 * @Desc
 */
@Named
public class DeviceDataPlanService implements IDeviceDataPlanService {

    @Inject
    private IDeviceDataPlanDao deviceDataPlanDao;

    @Inject
    private ICommonService commonService;

    @Override
    public PageResult<DeviceDpVO> findPageDeviceDpResult(DeviceDpVO vo, PageVO pageVO) {
        // 没有传商户过滤，获取商户及商户下的所有商户
        List<CommonVO> list = new ArrayList<>();
        if (vo.getEnterpriseId() == null) {
            list = commonService.findEnterpriseListByParentId(vo.getCurrentEnterpriseId());
        }
        return deviceDataPlanDao.findPageDeviceDpResult(vo, pageVO, list);
    }

    @Override
    public DeviceDpVO findDeviceDpDetail(String id, DeviceDpVO vo) {
        vo.setDpOrderId(id);
        return deviceDataPlanDao.findDeviceDpDetail(vo);
    }

    @Override
    public List<DeviceDpVO> findDeviceDpList(String deviceSn, DeviceDpVO vo) {
        vo.setDeviceSn(deviceSn);
        return deviceDataPlanDao.findDeviceDpList(vo);
    }
}
