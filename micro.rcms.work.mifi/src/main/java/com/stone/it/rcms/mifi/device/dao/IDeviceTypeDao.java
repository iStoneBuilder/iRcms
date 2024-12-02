package com.stone.it.rcms.mifi.device.dao;

import com.stone.it.rcms.core.vo.PageResult;
import com.stone.it.rcms.core.vo.PageVO;
import com.stone.it.rcms.mifi.device.vo.DeviceTypeVO;
import org.apache.ibatis.annotations.Param;

/**
 *
 * @author cj.stone
 * @Date 2024/12/1
 * @Desc
 */
public interface IDeviceTypeDao {

    PageResult<DeviceTypeVO> findDeviceTypePageResult(DeviceTypeVO deviceTypeVO, PageVO pageVO);

    DeviceTypeVO findDeviceTypeDetail(@Param("typeId") String id);

    int createDeviceType(DeviceTypeVO deviceTypeVO);

    int updateDeviceType(DeviceTypeVO deviceTypeVO);

    int deleteDeviceType(DeviceTypeVO deviceTypeVO);
}
