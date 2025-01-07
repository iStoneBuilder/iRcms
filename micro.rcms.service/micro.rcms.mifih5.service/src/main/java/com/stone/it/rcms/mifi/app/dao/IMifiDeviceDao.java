package com.stone.it.rcms.mifi.app.dao;

import com.stone.it.rcms.mifi.app.vo.MifiControlVO;
import com.stone.it.rcms.mifi.app.vo.MifiDeviceVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *
 * @author cj.stone
 * @Date 2024/12/30
 * @Desc
 */
public interface IMifiDeviceDao {
    MifiDeviceVO findUserUsedDevice(MifiDeviceVO deviceVO);

    List<MifiDeviceVO> findUserDevices(MifiDeviceVO deviceVO);

    int changeCurrentDevice(@Param("deviceSn") String deviceSn, @Param("vo") MifiDeviceVO deviceVO);

    int createChangeRecord(MifiControlVO controlVO);
}
