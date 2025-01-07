package com.stone.it.rcms.mifi.sim.service.impl;

import com.stone.it.rcms.core.exception.RcmsApplicationException;
import com.stone.it.rcms.core.util.RandomUtil;
import com.stone.it.rcms.core.util.UserUtil;
import com.stone.it.rcms.core.vo.PageResult;
import com.stone.it.rcms.core.vo.PageVO;
import com.stone.it.rcms.mifi.sim.dao.IMerchantDao;
import com.stone.it.rcms.mifi.sim.service.IMerchantService;
import com.stone.it.rcms.mifi.sim.vo.CarrierVO;
import com.stone.it.rcms.mifi.sim.vo.MerchantVO;
import java.util.List;
import javax.inject.Inject;
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

    @Inject
    private IMerchantDao merchantDao;

    @Override
    public PageResult<MerchantVO> findMerchantPageResult(MerchantVO merchantVO, PageVO pageVO) {
        return merchantDao.findMerchantPageResult(merchantVO, pageVO);
    }

    @Override
    public List<MerchantVO> findMerchantList(MerchantVO merchantVO) {
        return merchantDao.findMerchantList(merchantVO);
    }

    @Override
    public int createMerchant(MerchantVO merchantVO) {
        merchantVO.setMerchantCode(RandomUtil.stringGenerator(8));
        return merchantDao.createMerchant(merchantVO);
    }

    @Override
    public int updateMerchant(String code, MerchantVO merchantVO) {
        merchantVO.setMerchantCode(code);
        return merchantDao.updateMerchant(merchantVO);
    }

    @Override
    public int deleteMerchant(String code) {
        List<CarrierVO> list = merchantDao.findMerchantCarrierListByMerchantCode(code);
        if (!list.isEmpty()) {
            throw new RcmsApplicationException(500, "该卡商下存在运营商数据，请先删除运营商！");
        }
        return merchantDao.deleteMerchant(code, UserUtil.getEnterpriseId());
    }

    @Override
    public PageResult<CarrierVO> findMerchantCarrierPageResult(CarrierVO carrierVO, PageVO pageVO) {
        return merchantDao.findMerchantCarrierPageResult(carrierVO, pageVO);
    }

    @Override
    public int createMerchantCarrier(CarrierVO carrierVO) {
        return merchantDao.createMerchantCarrier(carrierVO);
    }

    @Override
    public int updateMerchantCarrier(String merchantCode, String carrierCode, CarrierVO carrierVO) {
        carrierVO.setMerchantCode(merchantCode);
        carrierVO.setCarrierCode(carrierCode);
        return merchantDao.updateMerchantCarrier(carrierVO);
    }

    @Override
    public int deleteMerchantCarrier(String merchantCode, String carrierCode) {
        return merchantDao.deleteMerchantCarrier(merchantCode, carrierCode);
    }
}
