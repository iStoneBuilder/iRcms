package com.stone.it.rcms.mifi.dataplan.service.impl;

import com.stone.it.rcms.core.exception.RcmsApplicationException;
import com.stone.it.rcms.core.util.RandomUtil;
import com.stone.it.rcms.core.util.UserUtil;
import com.stone.it.rcms.core.vo.PageResult;
import com.stone.it.rcms.core.vo.PageVO;
import com.stone.it.rcms.core.common.service.ICommonService;
import com.stone.it.rcms.core.common.vo.CommonVO;
import com.stone.it.rcms.mifi.dataplan.dao.IDataPlanSettingDao;
import com.stone.it.rcms.mifi.dataplan.service.IDataPlanSettingService;
import com.stone.it.rcms.mifi.dataplan.vo.DataPlanVO;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author cj.stone
 * @Date 2024/12/4
 * @Desc
 */
@Named
public class DataPlanSettingService implements IDataPlanSettingService {

    @Inject
    private IDataPlanSettingDao dataPlanSettingDao;

    @Inject
    private ICommonService commonService;

    @Override
    public PageResult<DataPlanVO> findPageDataPlanResult(DataPlanVO vo, PageVO pageVO) {
        // 没有传商户过滤，获取商户及商户下的所有商户
        List<CommonVO> list = new ArrayList<>();
        if (vo.getEnterpriseId() == null) {
            list = commonService.findEnterpriseListByParentId(vo.getCurrentEnterpriseId());
        }
        return dataPlanSettingDao.findPageDataPlanResult(vo, pageVO, list);
    }

    @Override
    public List<DataPlanVO> findDataPlanList(DataPlanVO vo) {
        // 值允许查询当前商户下的数据
        List<CommonVO> list = new ArrayList<>();
        if (vo.getEnterpriseId() == null) {
            list = commonService.findEnterpriseListByParentId(vo.getCurrentEnterpriseId());
        }
        return dataPlanSettingDao.findDataPlanList(vo, list);
    }

    @Override
    public DataPlanVO findDataPlanDetail(String id) {
        return dataPlanSettingDao.findDataPlanDetail(id, UserUtil.getTenantId());
    }

    @Override
    public DataPlanVO createDataPlan(DataPlanVO deviceTypeVO) {
        deviceTypeVO.setDataPlanNo(RandomUtil.stringGenerator(10));
        return dataPlanSettingDao.createDataPlan(deviceTypeVO);
    }

    @Override
    public int updateDataPlan(String id, DataPlanVO vo) {
        vo.setDataPlanNo(id);
        return dataPlanSettingDao.updateDataPlan(vo);
    }

    @Override
    public int updateDataPlanSell(String id, DataPlanVO vo) {
        vo.setDataPlanNo(id);
        return dataPlanSettingDao.updateDataPlanSell(vo);
    }

    @Override
    public int deleteDataPlan(String id) {
        DataPlanVO vo = dataPlanSettingDao.findDataPlanDetail(id, UserUtil.getTenantId());
        if (vo == null || !vo.getEnterpriseId().equals(UserUtil.getEnterpriseId())) {
            throw new RcmsApplicationException(500, "不允许删除非当前商户的数据");
        }
        // 判断是有订单存在，存在订单不允许删除
        return dataPlanSettingDao.deleteDataPlan(vo);
    }
}
