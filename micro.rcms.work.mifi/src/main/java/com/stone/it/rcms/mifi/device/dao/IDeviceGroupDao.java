package com.stone.it.rcms.mifi.device.dao;

import com.stone.it.rcms.core.vo.PageResult;
import com.stone.it.rcms.core.vo.PageVO;
import com.stone.it.rcms.mifi.device.vo.DeviceGroupVO;
import org.apache.ibatis.annotations.Param;

/**
 *
 * @author cj.stone
 * @Date 2024/12/2
 * @Desc
 */
public interface IDeviceGroupDao {

    PageResult<DeviceGroupVO> findPageDeviceGroupResult(DeviceGroupVO groupVO, PageVO pageVO);

    DeviceGroupVO findDeviceGroupDetail(@Param("id") String id, @Param("tenantId") String tenantId);

    int createDeviceGroup(DeviceGroupVO deviceTypeVO);

    int updateDeviceGroup(DeviceGroupVO groupVO);

    int deleteDeviceGroup(DeviceGroupVO vo);
}
