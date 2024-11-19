package com.stone.it.rcms.mifi.sim.service.impl;

import com.stone.it.rcms.core.vo.PageResult;
import com.stone.it.rcms.core.vo.PageVO;
import com.stone.it.rcms.mifi.sim.service.IMerchantService;
import com.stone.it.rcms.mifi.sim.vo.CarrierVO;
import com.stone.it.rcms.mifi.sim.vo.MerchantVO;
import javax.inject.Named;

/**
 * 卡商管理实现
 * 
 * @author cj.stone
 * @Date 2024/11/19
 * @Desc
 */
@Named
public class MerchantService implements IMerchantService {

    @Override
    public PageResult<MerchantVO> findMerchantPageResult(MerchantVO merchantVO, PageVO pageVO) {
        return null;
    }

    @Override
    public int createMerchant(MerchantVO merchantVO) {
        return 0;
    }

    @Override
    public int updateMerchant(String code, MerchantVO merchantVO) {
        return 0;
    }

    @Override
    public int deleteMerchant(String code) {
        return 0;
    }

    @Override
    public PageResult<CarrierVO> findMerchantCarrierPageResult(CarrierVO carrierVO, PageVO pageVO) {
        return null;
    }

    @Override
    public int createMerchantCarrier(MerchantVO merchantVO) {
        return 0;
    }

    @Override
    public int updateMerchantCarrier(String code, MerchantVO merchantVO) {
        return 0;
    }

    @Override
    public int deleteMerchantCarrier(String code) {
        return 0;
    }
}
