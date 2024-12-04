package com.stone.it.rcms.mifi.device.dao;

import com.stone.it.rcms.core.vo.PageResult;
import com.stone.it.rcms.core.vo.PageVO;
import com.stone.it.rcms.mifi.common.vo.CommonVO;
import com.stone.it.rcms.mifi.device.vo.DeviceGroupVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *
 * @author cj.stone
 * @Date 2024/12/2
 * @Desc
 */
public interface IDeviceGroupDao {

    PageResult<DeviceGroupVO> findPageDeviceGroupResult(@Param("param1") DeviceGroupVO groupVO,
        @Param("param2") PageVO pageVO, @Param("param3") List<CommonVO> list);

    DeviceGroupVO findDeviceGroupDetail(@Param("id") String id, @Param("tenantId") String tenantId);

    int createDeviceGroup(DeviceGroupVO deviceTypeVO);

    int updateDeviceGroup(DeviceGroupVO groupVO);

    int deleteDeviceGroup(DeviceGroupVO vo);

    List<DeviceGroupVO> findDeviceGroupList(@Param("groupVO") DeviceGroupVO groupVO,
        @Param("list") List<CommonVO> list);
}
