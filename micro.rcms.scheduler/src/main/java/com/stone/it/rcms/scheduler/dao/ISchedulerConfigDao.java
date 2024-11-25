package com.stone.it.rcms.scheduler.dao;

import com.stone.it.rcms.core.vo.PageResult;
import com.stone.it.rcms.core.vo.PageVO;
import com.stone.it.rcms.scheduler.vo.SchedulerVO;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 *
 * @author cj.stone
 * @Desc
 */
public interface ISchedulerConfigDao {

    PageResult<SchedulerVO> findQuartzPageResult(SchedulerVO schedulerVO, PageVO pageVO);

    int createQuartz(SchedulerVO schedulerVO);

    int deleteQuartz(@Param("quartzId") String quartzId, @Param("tenantId") String tenantId);

    int updateQuartz(SchedulerVO schedulerVO);

    List<SchedulerVO> findQuartzList(SchedulerVO schedulerVO);

    List<SchedulerVO> findQuartzListByGroupId(@Param("groupId") String groupId);

    SchedulerVO findQuartzInfoById(@Param("quartzId") String quartzId, @Param("tenantId") String tenantId);

}
