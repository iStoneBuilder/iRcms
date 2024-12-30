package com.stone.it.rcms.mifi.app.service.impl;

import com.stone.it.rcms.mifi.app.dao.IMifiDeviceDao;
import com.stone.it.rcms.mifi.app.dao.IMifiThaliDao;
import com.stone.it.rcms.mifi.app.service.IMifiDeviceService;
import com.stone.it.rcms.mifi.app.vo.*;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

/**
 *
 * @author cj.stone
 * @Date 2024/12/17
 * @Desc
 */
@Named
public class MifiDeviceService implements IMifiDeviceService {

    @Inject
    private IMifiDeviceDao mifiDeviceDao;

    @Inject
    private IMifiThaliDao mifiThaliDao;

    @Override
    public MifiDeviceVO findUserUsedDevice(MifiDeviceVO deviceVO) {
        // 查询设备信息
        MifiDeviceVO vo = mifiDeviceDao.findUserUsedDevice(deviceVO);
        if (vo == null) {
            return null;
        }
        // 查询设备可用套餐
        List<MifiThaliVO> list = mifiThaliDao.findDeviceCanUseThali(vo.getDeviceSn());
        if (list != null) {
            // 设置套餐组
            vo.setThaliNum(list.size());
            // 计算流量 monthly-charge
            int leftDay = 0;
            long thaliTotal = 0;
            long thaliSurplus = 0;
            for (MifiThaliVO thaliVO : list) {
                long useDay =
                    (System.currentTimeMillis() - thaliVO.getEffectiveTime().getTime()) / (24 * 60 * 60 * 1000);
                // 按月计费
                if ("monthly-charge".equals(thaliVO.getChargeType())) {
                    if (useDay > 30) {
                        int useDayMin = Math.toIntExact(useDay - (useDay / 30) * 30);
                        if ((30 - useDayMin) > leftDay) {
                            leftDay = 30 - useDayMin;
                        }
                    } else {
                        leftDay = Math.toIntExact(30 - useDay);
                    }
                } else {
                    if (leftDay < thaliVO.getValidDuration() - useDay) {
                        leftDay = Math.toIntExact(thaliVO.getValidDuration() - useDay);
                    }
                }
                thaliTotal = thaliTotal + thaliVO.getTotalFlow();
                thaliSurplus = thaliSurplus + thaliVO.getUsedFlow();
            }
            vo.setThaliSurplusDay(leftDay);
            vo.setThaliTotal(thaliTotal);
            vo.setThaliSurplus(thaliSurplus);
        }
        return vo;
    }

    @Override
    public List<MifiDeviceVO> findUserDevices(MifiDeviceVO deviceVO) {
        return List.of();
    }

    @Override
    public int changeCurrentDevice(String deviceSn, MifiDeviceVO deviceVO) {
        return 0;
    }

    @Override
    public int changeCurrentDeviceWifi(String deviceSn, MifiWifiVO wifiVO) {
        return 0;
    }

    @Override
    public int changeCurrentDeviceIccid(String deviceSn, MifiIccidVO iccidVO) {
        return 0;
    }

    @Override
    public List<MifiCheckVO> checkCurrentDevice(String deviceSn, MifiDeviceVO deviceVO) {
        return List.of();
    }
}
