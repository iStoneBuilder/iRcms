package com.stone.it.rcms.mifi.sim.dao;

import com.stone.it.rcms.core.common.vo.CommonVO;
import com.stone.it.rcms.core.vo.PageResult;
import com.stone.it.rcms.core.vo.PageVO;
import com.stone.it.rcms.mifi.device.vo.DeviceDivideVO;
import com.stone.it.rcms.mifi.device.vo.DeviceVO;
import com.stone.it.rcms.mifi.sim.vo.SimVO;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 *
 * @author cj.stone
 * @Date 2024/11/19
 * @Desc
 */
public interface ISimDao {

    PageResult<SimVO> findSimPageResult(@Param("param1") SimVO simVO, @Param("param2") PageVO pageVO,
        @Param("param3") List<CommonVO> list);

    int createSim(@Param("list") List<SimVO> list);

    int updateSim(SimVO simVO);

    int deleteSim(SimVO simVO);

    List<String> findNotExistIccid(@Param("list") List<SimVO> list, @Param("tenantId") String tenantId);

    int updateSimRelationship(@Param("list") List<DeviceVO> noExists);

    void updateSimMch(@Param("list") List<DeviceVO> list, @Param("divide") DeviceDivideVO divideVO);

    SimVO findSimDetail(SimVO simVO);

    int updateSimDeviceInfo(DeviceVO detail);
}
