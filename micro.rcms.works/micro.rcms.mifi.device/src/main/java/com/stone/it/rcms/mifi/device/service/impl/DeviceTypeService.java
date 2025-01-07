package com.stone.it.rcms.mifi.device.service.impl;

import com.stone.it.rcms.core.exception.RcmsApplicationException;
import com.stone.it.rcms.core.util.UUIDUtil;
import com.stone.it.rcms.core.util.UserUtil;
import com.stone.it.rcms.core.vo.PageResult;
import com.stone.it.rcms.core.vo.PageVO;
import com.stone.it.rcms.mifi.device.dao.IDeviceTypeDao;
import com.stone.it.rcms.mifi.device.service.IDeviceTypeService;
import com.stone.it.rcms.mifi.device.vo.DeviceTypeVO;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author cj.stone
 * @Date 2024/12/1
 * @Desc
 */
@Named
public class DeviceTypeService implements IDeviceTypeService {

    @Inject
    private IDeviceTypeDao deviceTypeDao;

    @Override
    public PageResult<DeviceTypeVO> findPageDeviceTypeResult(DeviceTypeVO deviceTypeVO, PageVO pageVO) {
        return deviceTypeDao.findDeviceTypePageResult(deviceTypeVO, pageVO);
    }

    @Override
    public List<DeviceTypeVO> findDeviceTypeList(DeviceTypeVO deviceTypeVO) {
        return deviceTypeDao.findDeviceTypeList(deviceTypeVO);
    }

    @Override
    public DeviceTypeVO findDeviceTypeDetail(String id) {
        DeviceTypeVO vo = deviceTypeDao.findDeviceTypeDetail(id);
        if (vo != null && vo.getTenantId().equals(UserUtil.getTenantId())) {
            return vo;
        } else {
            throw new RcmsApplicationException(500, "设备类型不存在");
        }
    }

    @Override
    public DeviceTypeVO createDeviceType(DeviceTypeVO deviceTypeVO) {
        deviceTypeVO.setTypeId(UUIDUtil.getUuid());
        deviceTypeDao.createDeviceType(deviceTypeVO);
        return deviceTypeVO;
    }

    @Override
    public int updateDeviceType(String id, DeviceTypeVO deviceTypeVO) {
        deviceTypeVO.setTypeId(id);
        return deviceTypeDao.updateDeviceType(deviceTypeVO);
    }

    @Override
    public int deleteDeviceType(String id) {
        DeviceTypeVO vo = deviceTypeDao.findDeviceTypeDetail(id);
        // 判断类型下是否存在设备，存在标记为无效，否则物理删除
        return deviceTypeDao.deleteDeviceType(vo);
    }
}
