package com.stone.it.micro.rcms.scheduler.service;

import com.stone.it.micro.rcms.framecom.vo.PageResult;
import com.stone.it.micro.rcms.framecom.vo.PageVO;
import com.stone.it.micro.rcms.scheduler.vo.SchedulerVO;

/**
 *
 * @author cj.stone
 * @Date 2023/7/19
 * @Desc
 */
public interface ISchedulerService {

  PageResult<SchedulerVO> findQuartzPageResult(SchedulerVO schedulerVO, PageVO pageVO);

  int createQuartz(SchedulerVO schedulerVO);

  int deleteQuartz(String jobId);

  int updateQuartz(SchedulerVO schedulerVO);

}
