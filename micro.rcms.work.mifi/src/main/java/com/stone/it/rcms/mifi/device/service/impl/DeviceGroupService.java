package com.stone.it.rcms.mifi.device.service.impl;

import com.stone.it.rcms.core.exception.RcmsApplicationException;
import com.stone.it.rcms.core.util.RandomUtil;
import com.stone.it.rcms.core.util.UserUtil;
import com.stone.it.rcms.core.vo.PageResult;
import com.stone.it.rcms.core.vo.PageVO;
import com.stone.it.rcms.mifi.device.dao.IDeviceGroupDao;
import com.stone.it.rcms.mifi.device.service.IDeviceGroupService;
import com.stone.it.rcms.mifi.device.vo.DeviceGroupVO;
import javax.inject.Inject;
import javax.inject.Named;

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

    @Override
    public PageResult<DeviceGroupVO> findPageDeviceGroupResult(DeviceGroupVO groupVO, PageVO pageVO) {
        return deviceGroupDao.findPageDeviceGroupResult(groupVO, pageVO);
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
        return deviceGroupDao.deleteDeviceGroup(vo);
    }
}
