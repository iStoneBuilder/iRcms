package com.stone.it.rcms.mifi.device.service.impl;

import com.alibaba.fastjson2.JSONObject;
import com.stone.it.rcms.core.common.service.ICommonService;
import com.stone.it.rcms.core.common.vo.CommonVO;
import com.stone.it.rcms.core.common.vo.DpEventVO;
import com.stone.it.rcms.core.util.DateUtil;
import com.stone.it.rcms.core.util.UUIDUtil;
import com.stone.it.rcms.core.vo.PageResult;
import com.stone.it.rcms.core.vo.PageVO;
import com.stone.it.rcms.mifi.dataplan.dao.IDataPlanSettingDao;
import com.stone.it.rcms.mifi.device.vo.DeviceDpVO;
import com.stone.it.rcms.mifi.dataplan.vo.DataPlanVO;
import com.stone.it.rcms.mifi.device.dao.IDeviceDataPlanDao;
import com.stone.it.rcms.mifi.device.service.IDeviceDataPlanService;
import lombok.SneakyThrows;
import org.springframework.context.event.EventListener;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author cj.stone
 * @Date 2024/12/10
 * @Desc
 */
@Named
public class DeviceDataPlanService implements IDeviceDataPlanService {
    @Inject
    private IDataPlanSettingDao settingDao;

    @Inject
    private IDeviceDataPlanDao deviceDataPlanDao;

    @Inject
    private ICommonService commonService;

    @Override
    @SneakyThrows
    @EventListener(DpEventVO.class)
    public int createDeviceDp(DpEventVO eventVO) {
        if (eventVO == null) {
            return 0;
        }
        // 创建套餐
        if ("create".equals(eventVO.getEventContext())) {
            return createDdp(eventVO);
        }
        // 套餐退款
        if ("refund".equals(eventVO.getEventType())) {
            // 解析数据
            DeviceDpVO devDpVO = JSONObject.parseObject(eventVO.getEventContext(), DeviceDpVO.class);
            return deviceDataPlanDao.refundDeviceDp(devDpVO);
        }
        return 1;
    }

    private int createDdp(DpEventVO eventVO) {
        // 解析数据
        DeviceDpVO devDpVO = JSONObject.parseObject(eventVO.getEventContext(), DeviceDpVO.class);
        // 根据套餐ID查询 套餐信息
        DataPlanVO dpVO = settingDao.findDataPlanDetail(devDpVO.getDataPlanNo(), devDpVO.getTenantId());
        // 设置订单编号
        devDpVO.setDpOrderId(UUIDUtil.getUuid());
        // 设置失效时间
        devDpVO
            .setExpireTime(DateUtil.addDaysToDate(devDpVO.getExpireTime(), Integer.parseInt(dpVO.getValidDuration())));
        // 设置套餐流量信息(GB->MB)
        devDpVO.setTotalFlow(dpVO.getDataPlanFlow() * 1024);
        devDpVO.setRemainFlow(0L);
        devDpVO.setUsedFlow(0L);
        devDpVO.setIsAvailable("Y");
        devDpVO.setIsLimitSpeed(dpVO.getIsLimitSpeed());
        return deviceDataPlanDao.createDeviceDp(devDpVO);
    }

    @Override
    public PageResult<DeviceDpVO> findPageDeviceDpResult(DeviceDpVO vo, PageVO pageVO) {
        // 没有传商户过滤，获取商户及商户下的所有商户
        List<CommonVO> list = new ArrayList<>();
        if (vo.getEnterpriseId() == null) {
            list = commonService.findEnterpriseListByParentId(vo.getCurrentEnterpriseId());
        }
        return deviceDataPlanDao.findPageDeviceDpResult(vo, pageVO, list);
    }

    @Override
    public DeviceDpVO findDeviceDpDetail(String id, DeviceDpVO vo) {
        vo.setDpOrderId(id);
        return deviceDataPlanDao.findDeviceDpDetail(vo);
    }

    @Override
    public List<DeviceDpVO> findDeviceDpList(String deviceSn, DeviceDpVO vo) {
        vo.setDeviceSn(deviceSn);
        return deviceDataPlanDao.findDeviceDpList(vo);
    }
}
