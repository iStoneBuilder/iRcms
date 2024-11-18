package com.stone.it.rcms.scheduler.service.impl;


import com.stone.it.rcms.core.vo.PageResult;
import com.stone.it.rcms.core.vo.PageVO;
import com.stone.it.rcms.scheduler.dao.ISchedulerJobDao;
import com.stone.it.rcms.scheduler.service.ISchedulerJobService;
import com.stone.it.rcms.scheduler.vo.QuartzJobVO;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author cj.stone
 * @Desc
 */
@Named
public class SchedulerJobService implements ISchedulerJobService {

  @Inject
  private ISchedulerJobDao schedulerJobDao;

  @Override
  public PageResult<QuartzJobVO> findQuartzJobPageResult(QuartzJobVO quartzJobVO, PageVO pageVO) {
    return schedulerJobDao.findQuartzJobPageResult(quartzJobVO,pageVO);
  }
}
