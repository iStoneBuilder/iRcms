package com.stone.it.rcms.mifi.sim.dao;

import com.stone.it.rcms.core.vo.PageResult;
import com.stone.it.rcms.core.vo.PageVO;
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

    PageResult<SimVO> findSimPageResult(SimVO simVO, PageVO pageVO);

    int createSim(@Param("list") List<SimVO> list);

    int updateSim(SimVO simVO);

    int deleteSim(SimVO simVO);

    List<String> findNotExistIccid(@Param("list") List<SimVO> list, @Param("tenantId") String tenantId);
}
