package com.stone.it.rcms.mifi.sim.service.impl;

import com.alibaba.fastjson2.JSONObject;
import com.stone.it.rcms.core.common.service.ICommonService;
import com.stone.it.rcms.core.common.vo.CommonVO;
import com.stone.it.rcms.core.util.DateUtil;
import com.stone.it.rcms.core.util.UserUtil;
import com.stone.it.rcms.core.vo.PageResult;
import com.stone.it.rcms.core.vo.PageVO;
import com.stone.it.rcms.mifi.sim.beiwei.BeiWeiSimOperateService;
import com.stone.it.rcms.mifi.sim.dao.IMerchantDao;
import com.stone.it.rcms.mifi.sim.dao.ISimDao;
import com.stone.it.rcms.mifi.sim.service.ISimService;
import com.stone.it.rcms.mifi.sim.vo.CarrierVO;
import com.stone.it.rcms.mifi.sim.vo.SimVO;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * SIM卡管理 企业级数据
 * 
 * @author cj.stone
 * @Date 2024/11/19
 * @Desc
 */
@Named
public class SimService implements ISimService {

    @Inject
    private ISimDao simDao;
    @Inject
    private IMerchantDao merchantDao;
    @Inject
    private ICommonService commonService;

    @Override
    public PageResult<SimVO> findSimPageResult(SimVO simVO, PageVO pageVO) {
        // 没有传商户过滤，获取商户及商户下的所有商户
        List<CommonVO> list = new ArrayList<>();
        if (simVO.getEnterpriseId() == null) {
            list = commonService.findEnterpriseListByParentId(simVO.getCurrentEnterpriseId());
        }
        return simDao.findSimPageResult(simVO, pageVO, list);
    }

    @Override
    public SimVO findSimDetail(String iccid, SimVO simVO) {
        simVO.setIccid(iccid);
        SimVO infoVO = simDao.findSimDetail(simVO);
        // 查询运营商信息
        infoVO.setCarrierInfo(new JSONObject());
        return infoVO;
    }

    @Override
    public int createSim(List<SimVO> list) {
        // 查询存在的数据
        List<String> existList = simDao.findNotExistIccid(list, UserUtil.getTenantId());
        // 排除已存在的数据
        if (!existList.isEmpty()) {
            list.removeIf(item -> existList.contains(item.getIccid()));
        }
        if (!list.isEmpty()) {
            return simDao.createSim(list);
        }
        return 0;
    }

    @Override
    public int updateSim(String iccid, SimVO simVO) {
        return simDao.updateSim(simVO);
    }

    @Override
    public int deleteSim(String iccid) {
        SimVO simVO = new SimVO();
        simVO.setIccid(iccid);
        simVO.setTenantId(UserUtil.getTenantId());
        return simDao.deleteSim(simVO);
    }

    @Override
    public int syncSimDp(String iccid, SimVO simVO) {
        simVO.setIccid(iccid);
        // 查询卡商信息
        CarrierVO carrierVO = merchantDao.findMerchantCarrierInfoByIccId(iccid);
        // 查询当月信息
        JSONObject monthFlow = BeiWeiSimOperateService.queryMonthFlow(iccid, DateUtil.formatDate("yyyyMM"), carrierVO);
        if (monthFlow != null) {
            if (monthFlow.containsKey("left")) {
                simVO.setFlowRemain(monthFlow.getDouble("left"));
            }
            if (monthFlow.containsKey("used")) {
                simVO.setFlowUsed(monthFlow.getDouble("used"));
            }
        }
        // 查询当日信息
        Double dayFlow = BeiWeiSimOperateService.queryDayFlow(iccid, DateUtil.formatDate("yyyyMMdd"), carrierVO);
        simVO.setFlowUsedDay(dayFlow);
        return 0;
    }

}
