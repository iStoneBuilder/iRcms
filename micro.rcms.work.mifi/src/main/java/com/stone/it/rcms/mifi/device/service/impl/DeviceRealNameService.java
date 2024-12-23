package com.stone.it.rcms.mifi.device.service.impl;

import com.alibaba.fastjson2.JSONObject;
import com.stone.it.rcms.core.vo.PageResult;
import com.stone.it.rcms.core.vo.PageVO;
import com.stone.it.rcms.mifi.device.dao.IDeviceRealNameDao;
import com.stone.it.rcms.mifi.device.service.IDeviceRealNameService;
import com.stone.it.rcms.mifi.sim.beiwei.BeiWeiSimOperateService;
import com.stone.it.rcms.mifi.sim.dao.IMerchantDao;
import com.stone.it.rcms.mifi.sim.vo.CarrierVO;
import com.stone.it.rcms.mifi.sim.vo.SimNameVO;
import com.stone.it.rcms.mifi.sim.vo.SimVO;

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
    private IMerchantDao merchantDao;
    @Inject
    private IDeviceRealNameDao deviceRealNameDao;

    @Override
    public PageResult<SimNameVO> findSimNamePageResult(SimNameVO simNameVO, PageVO pageVO) {
        return deviceRealNameDao.findSimNamePageResult(simNameVO, pageVO);
    }

    @Override
    public int createSimName(SimNameVO simNameVO) {
        return deviceRealNameDao.createSimName(simNameVO);
    }

    @Override
    public int syncSimName(String iccid, SimNameVO simNameVO) {
        SimVO simVO = new SimVO();
        simVO.setIccid(iccid);
        // 查询卡商信息
        CarrierVO carrierVO = merchantDao.findMerchantCarrierInfoByIccId(iccid);
        JSONObject nameStatus = BeiWeiSimOperateService.queryRealNameStatus(iccid, carrierVO);
        simNameVO.setIccid(iccid);
        if (nameStatus != null) {
            if (nameStatus.containsKey("flag") && nameStatus.getString("flag") != null) {
                simNameVO.setAuthStatus(nameStatus.getString("flag"));
            }
            return deviceRealNameDao.syncSimName(simNameVO);
        }
        return 0;
    }

    @Override
    public int cleanSimName(String iccid, SimNameVO simNameVO) {
        return deviceRealNameDao.cleanSimName(simNameVO);
    }
}
