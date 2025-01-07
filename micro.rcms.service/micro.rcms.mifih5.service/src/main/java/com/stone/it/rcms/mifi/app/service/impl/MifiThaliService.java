package com.stone.it.rcms.mifi.app.service.impl;

import com.stone.it.rcms.mifi.app.service.IMifiThaliService;
import com.stone.it.rcms.mifi.app.vo.MifiThaliVO;

import java.util.List;

/**
 *
 * @author cj.stone
 * @Date 2024/12/17
 * @Desc
 */
public class MifiThaliService implements IMifiThaliService {
    @Override
    public List<MifiThaliVO> findDeviceThaliList(String deviceSn, MifiThaliVO vo) {
        return List.of();
    }

    @Override
    public MifiThaliVO findThaliDetail(String dataPlanNo, MifiThaliVO vo) {
        return null;
    }
}
