package com.stone.it.rcms.mifi.device.service.impl;

import com.stone.it.rcms.core.common.service.ICommonService;
import com.stone.it.rcms.core.common.vo.CommonVO;
import com.stone.it.rcms.core.util.DateUtil;
import com.stone.it.rcms.core.vo.PageResult;
import com.stone.it.rcms.core.vo.PageVO;
import com.stone.it.rcms.mifi.device.dao.IDeviceDivideDao;
import com.stone.it.rcms.mifi.device.dao.IDeviceManageDao;
import com.stone.it.rcms.mifi.device.service.IDeviceDivideService;
import com.stone.it.rcms.mifi.device.vo.DeviceDivideVO;
import com.stone.it.rcms.mifi.sim.dao.ISimDao;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author cj.stone
 * @Date 2024/12/11
 * @Desc
 */
@Named
public class DeviceDivideService implements IDeviceDivideService {

    @Inject
    private IDeviceManageDao deviceManageDao;

    @Inject
    private IDeviceDivideDao deviceDivideDao;

    @Inject
    private ISimDao simDao;

    @Inject
    private ICommonService commonService;

    @Override
    public PageResult<DeviceDivideVO> findPageDivideResult(DeviceDivideVO vo, PageVO pageVO) {
        // 处理增加了查询条件
        List<CommonVO> list = new ArrayList<>();
        if (vo.getEnterpriseId() == null) {
            list = commonService.findEnterpriseListByParentId(vo.getCurrentEnterpriseId());
        }
        return deviceDivideDao.findPageDivideResult(vo, pageVO, list);
    }

    @Override
    public void divideDevice(DeviceDivideVO divideVO) {
        // 更新设备商户，分组信息
        deviceManageDao.updateDeviceGroupMch(divideVO.getList(), divideVO);
        // 更新设备ICCID信息
        simDao.updateSimMch(divideVO.getList(), divideVO);
        divideVO.setDivideId(DateUtil.formatDate());
        divideVO.setDivideNum(divideVO.getList().size());
        divideVO.setDivideStatus("SUCCESS");
        divideVO.setRemark("设备分发成功");
        // 生成分发记录
        deviceDivideDao.createDivideRecord(divideVO, divideVO.getList());
    }
}
