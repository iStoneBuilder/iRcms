package com.stone.it.rcms.mifi.device.dao;

import com.stone.it.rcms.core.vo.PageResult;
import com.stone.it.rcms.core.vo.PageVO;
import com.stone.it.rcms.core.common.vo.CommonVO;
import com.stone.it.rcms.mifi.device.vo.DeviceDivideVO;
import com.stone.it.rcms.mifi.device.vo.DeviceVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *
 * @author cj.stone
 * @Date 2024/11/19
 * @Desc
 */
public interface IDeviceManageDao {

    PageResult<DeviceVO> findPageDeviceResult(@Param("param1") DeviceVO vo, @Param("param2") PageVO pageVO,
        @Param("param3") List<CommonVO> list);

    DeviceVO findDeviceDetail(@Param("deviceSn") String deviceSn);

    List<DeviceVO> findExistDevice(@Param("list") List<DeviceVO> list);

    int createDevice(@Param("list") List<DeviceVO> list);

    int updateDevice(DeviceVO vo);

    int deleteDevice(@Param("deviceSn") String deviceSn);

    int setDeviceGroup(@Param("list") List<DeviceVO> list);

    /** 更新设备商户信息，清空分组信息 */
    int updateDeviceGroupMch(@Param("list") List<DeviceVO> list, @Param("divide") DeviceDivideVO divideVO);

    List<DeviceVO> findDeviceListByGroupCode(@Param("groupCode") String groupCode);
}
