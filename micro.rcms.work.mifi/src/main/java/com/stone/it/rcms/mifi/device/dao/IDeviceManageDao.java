package com.stone.it.rcms.mifi.device.dao;

import com.stone.it.rcms.core.vo.PageResult;
import com.stone.it.rcms.core.vo.PageVO;
import com.stone.it.rcms.mifi.common.vo.CommonVO;
import com.stone.it.rcms.mifi.device.vo.DeviceVO;

import java.util.List;

/**
 *
 * @author cj.stone
 * @Date 2024/11/19
 * @Desc
 */
public interface IDeviceManageDao {

    PageResult<DeviceVO> findPageDeviceResult(DeviceVO vo, PageVO pageVO, List<CommonVO> list);

    DeviceVO findDeviceDetail(String deviceSn);

    List<DeviceVO> findExistDevice(List<DeviceVO> list);

    int createDevice(List<DeviceVO> list);

    int updateDevice(DeviceVO vo);

    int deleteDevice(String deviceSn);
}
