package com.stone.it.rcms.mifi.device.dao;

import com.stone.it.rcms.core.vo.PageResult;
import com.stone.it.rcms.core.vo.PageVO;
import com.stone.it.rcms.mifi.device.vo.DeviceNameVO;

/**
 *
 * @author cj.stone
 * @Date 2024/12/23
 * @Desc
 */
public interface IDeviceRealNameDao {

    PageResult<DeviceNameVO> findSimNamePageResult(DeviceNameVO deviceNameVO, PageVO pageVO);

    int createSimName(DeviceNameVO deviceNameVO);

    int syncSimName(DeviceNameVO deviceNameVO);

    int cleanSimName(DeviceNameVO deviceNameVO);
}
