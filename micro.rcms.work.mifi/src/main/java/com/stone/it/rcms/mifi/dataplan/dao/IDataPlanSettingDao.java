package com.stone.it.rcms.mifi.dataplan.dao;

import com.stone.it.rcms.core.vo.PageResult;
import com.stone.it.rcms.core.vo.PageVO;
import com.stone.it.rcms.mifi.common.vo.CommonVO;
import com.stone.it.rcms.mifi.dataplan.vo.DataPlanVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *
 * @author cj.stone
 * @Date 2024/12/4
 * @Desc
 */
public interface IDataPlanSettingDao {
    PageResult<DataPlanVO> findPageDataPlanResult(@Param("param1") DataPlanVO vo, @Param("param2") PageVO pageVO,
        @Param("param3") List<CommonVO> list);

    List<DataPlanVO> findDataPlanList(DataPlanVO vo);

    DataPlanVO findDataPlanDetail(@Param("dataPlanNo") String id, @Param("tenantId") String tenantId);

    int deleteDataPlan(DataPlanVO vo);

    int updateDataPlan(DataPlanVO vo);

    DataPlanVO createDataPlan(DataPlanVO deviceTypeVO);

    int updateDataPlanSell(DataPlanVO vo);
}
