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

  List<SchedulerVO> findQuartzList(SchedulerVO schedulerVO);

  int createQuartz(SchedulerVO schedulerVO);

  int deleteQuartz(String jobId);

  int updateQuartz(SchedulerVO schedulerVO);

  SchedulerVO findQuartzInfo(@Param("quartzId")String quartzId);

}
