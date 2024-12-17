package com.stone.it.rcms.mifi.app.service.impl;

import com.stone.it.rcms.mifi.app.service.IMifiDeviceService;
import com.stone.it.rcms.mifi.app.vo.MifiDeviceVO;

import java.util.List;

/**
 *
 * @author cj.stone
 * @Date 2024/12/17
 * @Desc
 */
public class MifiDeviceService implements IMifiDeviceService {
    @Override
    public List<MifiDeviceVO> findUserDevices(MifiDeviceVO deviceVO) {
        return List.of();
    }

    @Override
    public MifiDeviceVO findDeviceDetailBySn(String deviceSn, MifiDeviceVO deviceVO) {
        return null;
    }
}
