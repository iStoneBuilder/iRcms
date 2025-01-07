package com.stone.it.rcms.mifi.device.service.impl;

import com.alibaba.fastjson2.JSONObject;
import com.stone.it.rcms.core.vo.PageResult;
import com.stone.it.rcms.core.vo.PageVO;
import com.stone.it.rcms.mifi.common.beiwai.BeiWeiSimOperateService;
import com.stone.it.rcms.mifi.common.service.IMifiCommonService;
import com.stone.it.rcms.mifi.common.vo.CarrierVO;
import com.stone.it.rcms.mifi.device.dao.IDeviceRealNameDao;
import com.stone.it.rcms.mifi.device.service.IDeviceRealNameService;
import com.stone.it.rcms.mifi.device.vo.DeviceNameVO;

import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author cj.stone
 * @Date 2024/12/23
 * @Desc
 */
@Named
public class DeviceRealNameService implements IDeviceRealNameService {

    @Inject
    private IMifiCommonService mifiCommonService;

    @Inject
    private IDeviceRealNameDao deviceRealNameDao;

    @Override
    public PageResult<DeviceNameVO> findSimNamePageResult(DeviceNameVO deviceNameVO, PageVO pageVO) {
        return deviceRealNameDao.findSimNamePageResult(deviceNameVO, pageVO);
    }

    @Override
    public int createSimName(DeviceNameVO deviceNameVO) {
        return deviceRealNameDao.createSimName(deviceNameVO);
    }

    @Override
    public int syncSimName(String iccid, DeviceNameVO deviceNameVO) {
        // 查询卡商信息
        CarrierVO carrierVO = mifiCommonService.findMerchantCarrierInfoByIccId(iccid);
        JSONObject nameStatus = BeiWeiSimOperateService.queryRealNameStatus(iccid, carrierVO);
        deviceNameVO.setIccid(iccid);
        if (nameStatus != null) {
            if (nameStatus.containsKey("flag") && nameStatus.getString("flag") != null) {
                deviceNameVO.setAuthStatus(nameStatus.getString("flag"));
                return deviceRealNameDao.syncSimName(deviceNameVO);
            }
        }
        return 0;
    }

    @Override
    public int cleanSimName(String iccid, DeviceNameVO deviceNameVO) {
        return deviceRealNameDao.cleanSimName(deviceNameVO);
    }
}
