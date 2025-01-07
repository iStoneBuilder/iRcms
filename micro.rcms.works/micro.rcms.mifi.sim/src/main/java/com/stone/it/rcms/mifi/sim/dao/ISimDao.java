package com.stone.it.rcms.mifi.sim.dao;

import com.stone.it.rcms.core.common.vo.CommonVO;
import com.stone.it.rcms.core.vo.PageResult;
import com.stone.it.rcms.core.vo.PageVO;
import com.stone.it.rcms.mifi.sim.vo.SimStatusVO;
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

    int createSim(@Param("list") List<SimVO> list, @Param("batchNo") String batchNo);

    int updateSim(SimVO simVO);

    int deleteSim(SimVO simVO);

    List<String> findNotExistIccid(@Param("list") List<SimVO> list, @Param("tenantId") String tenantId);

    SimVO findSimDetail(SimVO simVO);

    int syncSimDp(SimVO simVO);

    int createSimFlowStatus(@Param("sim") SimVO simVO, @Param("reqId") String reqId,
        @Param("operateType") String operateType);

    List<SimStatusVO> findSimStatusChangeInfo(@Param("iccid") String iccid);

    int updateSimStatus(SimVO simVO);

    List<SimStatusVO> findSimStatusChangeList(SimStatusVO simStatusVO);

    int updateSimOpenStopStatus(SimStatusVO item);
}
