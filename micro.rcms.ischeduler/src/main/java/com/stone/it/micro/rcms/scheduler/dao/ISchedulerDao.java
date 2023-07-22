package com.stone.it.micro.rcms.scheduler.dao;

import com.stone.it.micro.rcms.framecom.vo.PageResult;
import com.stone.it.micro.rcms.framecom.vo.PageVO;
import com.stone.it.micro.rcms.scheduler.vo.QuartzJobVO;
import com.stone.it.micro.rcms.scheduler.vo.SchedulerVO;
import java.util.List;

/**
 *
 * @author cj.stone
 * @Date 2023/7/19
 * @Desc
 */
public interface ISchedulerDao {

  /**
   * 查询所有配置的任务
   *
   * @param schedulerVO
   * @return
   */
  List<SchedulerVO> findQuartzList(SchedulerVO schedulerVO);

  PageResult<SchedulerVO> findQuartzPageResult(SchedulerVO schedulerVO, PageVO pageVO);

  int createQuartz(SchedulerVO schedulerVO);

  int deleteQuartz(String jobId);

  int updateQuartz(SchedulerVO schedulerVO);

  int createJob(QuartzJobVO quartzJobVO);

  int updateJob(QuartzJobVO quartzJobVO);

  SchedulerVO findQuartzInfo(String quartzId);
}
