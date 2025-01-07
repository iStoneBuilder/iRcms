package com.stone.it.rcms.mifi.common.service.impl;

import com.alibaba.fastjson2.JSONObject;
import com.stone.it.rcms.mifi.common.beiwai.BeiWeiSimOperateService;
import com.stone.it.rcms.mifi.common.dao.IMifiCommonDao;
import com.stone.it.rcms.mifi.common.service.IMifiCommonService;
import com.stone.it.rcms.mifi.common.vo.CarrierVO;
import com.stone.it.rcms.mifi.common.vo.SimAuthUrlVO;

import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author cj.stone
 * @Date 2025/1/7
 * @Desc
 */
@Named
public class MifiCommonService implements IMifiCommonService {

    @Inject
    private IMifiCommonDao mifiCommonDao;

    @Override
    public CarrierVO findMerchantCarrierInfoByIccId(String iccid) {
        return mifiCommonDao.findMerchantCarrierInfoByIccId(iccid);
    }

    @Override
    public SimAuthUrlVO authSimUrl(String iccid) {
        // 查询卡商信息
        CarrierVO carrierVO = findMerchantCarrierInfoByIccId(iccid);
        SimAuthUrlVO simAuthUrlVO = new SimAuthUrlVO();
        simAuthUrlVO.setIccid(iccid);
        JSONObject urlInfo = BeiWeiSimOperateService.queryRealNameUrl(iccid, carrierVO);
        simAuthUrlVO.setRealNameInfo(urlInfo);
        return simAuthUrlVO;
    }
}
