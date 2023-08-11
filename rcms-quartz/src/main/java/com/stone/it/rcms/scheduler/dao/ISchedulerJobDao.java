package com.stone.it.rcms.scheduler.dao;

import com.stone.it.micro.rcms.framecom.vo.PageResult;
import com.stone.it.micro.rcms.framecom.vo.PageVO;
import com.stone.it.rcms.scheduler.vo.QuartzJobVO;

/**
 *
 * @author cj.stone
 * @Date 2023/7/27
 * @Desc
 */
public interface ISchedulerJobDao {

  PageResult<QuartzJobVO> findQuartzJobPageResult(QuartzJobVO quartzJobVO, PageVO pageVO);
  int createJob(QuartzJobVO quartzJobVO);

  int updateJob(QuartzJobVO quartzJobVO);
}
