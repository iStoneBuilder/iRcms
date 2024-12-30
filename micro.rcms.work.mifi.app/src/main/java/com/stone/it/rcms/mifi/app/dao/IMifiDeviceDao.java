package com.stone.it.rcms.mifi.app.dao;

import com.stone.it.rcms.mifi.app.vo.MifiDeviceVO;

/**
 *
 * @author cj.stone
 * @Date 2024/12/30
 * @Desc
 */
public interface IMifiDeviceDao {
    MifiDeviceVO findUserUsedDevice(MifiDeviceVO deviceVO);
}
