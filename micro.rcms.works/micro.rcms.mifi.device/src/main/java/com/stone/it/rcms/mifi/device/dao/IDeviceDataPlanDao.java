package com.stone.it.rcms.mifi.device.dao;

import com.stone.it.rcms.core.common.vo.CommonVO;
import com.stone.it.rcms.core.vo.PageResult;
import com.stone.it.rcms.core.vo.PageVO;
import com.stone.it.rcms.mifi.device.vo.DeviceDpVO;

import java.util.List;

/**
 *
 * @author cj.stone
 * @Date 2024/12/10
 * @Desc
 */
public interface IDeviceDataPlanDao {

    int createDeviceDp(DeviceDpVO orderDpVO);

    PageResult<DeviceDpVO> findPageDeviceDpResult(DeviceDpVO vo, PageVO pageVO, List<CommonVO> list);

    DeviceDpVO findDeviceDpDetail(DeviceDpVO vo);

    List<DeviceDpVO> findDeviceDpList(DeviceDpVO vo);

    int refundDeviceDp(DeviceDpVO devDpVO);
}
