package com.stone.it.rcms.scheduler.dao;

import com.stone.it.rcms.core.vo.PageResult;
import com.stone.it.rcms.core.vo.PageVO;
import com.stone.it.rcms.scheduler.vo.QuartzJobVO;

/**
 *
 * @author cj.stone
 * @Desc
 */
public interface ISchedulerJobDao {

  PageResult<QuartzJobVO> findQuartzJobPageResult(QuartzJobVO quartzJobVO, PageVO pageVO);
  int createJob(QuartzJobVO quartzJobVO);

  int updateJob(QuartzJobVO quartzJobVO);
}
