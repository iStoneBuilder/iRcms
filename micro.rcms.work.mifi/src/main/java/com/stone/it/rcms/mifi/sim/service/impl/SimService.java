package com.stone.it.rcms.mifi.sim.service.impl;

import com.stone.it.rcms.core.util.UserUtil;
import com.stone.it.rcms.core.vo.PageResult;
import com.stone.it.rcms.core.vo.PageVO;
import com.stone.it.rcms.mifi.sim.dao.ISimDao;
import com.stone.it.rcms.mifi.sim.service.ISimService;
import com.stone.it.rcms.mifi.sim.vo.SimVO;
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

    @Override
    public PageResult<SimVO> findSimPageResult(SimVO simVO, PageVO pageVO) {
        return simDao.findSimPageResult(simVO, pageVO);
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
        return 0;
    }

    @Override
    public int limitSimDp(String iccid, SimVO simVO) {
        return 0;
    }
}
