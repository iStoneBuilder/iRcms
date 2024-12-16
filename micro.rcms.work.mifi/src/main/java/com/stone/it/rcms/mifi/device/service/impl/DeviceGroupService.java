package com.stone.it.rcms.mifi.device.service.impl;

import com.stone.it.rcms.core.exception.RcmsApplicationException;
import com.stone.it.rcms.core.util.RandomUtil;
import com.stone.it.rcms.core.util.UserUtil;
import com.stone.it.rcms.core.vo.PageResult;
import com.stone.it.rcms.core.vo.PageVO;
import com.stone.it.rcms.core.common.service.ICommonService;
import com.stone.it.rcms.core.common.vo.CommonVO;
import com.stone.it.rcms.mifi.device.dao.IDeviceGroupDao;
import com.stone.it.rcms.mifi.device.dao.IDeviceManageDao;
import com.stone.it.rcms.mifi.device.service.IDeviceGroupService;
import com.stone.it.rcms.mifi.device.vo.DeviceGroupVO;
import com.stone.it.rcms.mifi.device.vo.DeviceVO;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author cj.stone
 * @Date 2024/12/2
 * @Desc
 */
@Named
public class DeviceGroupService implements IDeviceGroupService {

    @Inject
    private IDeviceGroupDao deviceGroupDao;

    @Inject
    private ICommonService commonService;

    @Inject
    private IDeviceManageDao manageDao;

    @Override
    public PageResult<DeviceGroupVO> findPageDeviceGroupResult(DeviceGroupVO groupVO, PageVO pageVO) {
        // 没有传商户过滤，获取商户及商户下的所有商户
        List<CommonVO> list = new ArrayList<>();
        if (groupVO.getEnterpriseId() == null) {
            list = commonService.findEnterpriseListByParentId(groupVO.getCurrentEnterpriseId());
        }
        return deviceGroupDao.findPageDeviceGroupResult(groupVO, pageVO, list);
    }

    @Override
    public List<DeviceGroupVO> findDeviceGroupList(DeviceGroupVO groupVO) {
        // 没有传商户过滤，获取商户及商户下的所有商户
        List<CommonVO> list = new ArrayList<>();
        if (groupVO.getEnterpriseId() == null) {
            list = commonService.findEnterpriseListByParentId(groupVO.getCurrentEnterpriseId());
        }
        return deviceGroupDao.findDeviceGroupList(groupVO, list);
    }

    @Override
    public DeviceGroupVO findDeviceGroupDetail(String id) {
        DeviceGroupVO vo = deviceGroupDao.findDeviceGroupDetail(id, UserUtil.getTenantId());
        if (vo == null) {
            throw new RcmsApplicationException(500, "设备组不存在");
        }
        return vo;
    }

    @Override
    public DeviceGroupVO createDeviceGroup(DeviceGroupVO deviceTypeVO) {
        deviceTypeVO.setGroupId(RandomUtil.stringGenerator(10));
        deviceGroupDao.createDeviceGroup(deviceTypeVO);
        return deviceTypeVO;
    }

    @Override
    public int updateDeviceGroup(String id, DeviceGroupVO groupVO) {
        groupVO.setGroupId(id);
        return deviceGroupDao.updateDeviceGroup(groupVO);
    }

    @Override
    public int deleteDeviceGroup(String id) {
        DeviceGroupVO vo = findDeviceGroupDetail(id);
        List<DeviceVO> list = manageDao.findDeviceListByGroupCode(id);
        if (!list.isEmpty()) {
            throw new RcmsApplicationException(500, "该设备组下有设备，不能删除");
        }
        return deviceGroupDao.deleteDeviceGroup(vo);
    }
}
