package com.stone.it.rcms.scheduler.service.impl;


import com.stone.it.rcms.com.vo.PageResult;
import com.stone.it.rcms.com.vo.PageVO;
import com.stone.it.rcms.scheduler.dao.ISchedulerJobDao;
import com.stone.it.rcms.scheduler.service.ISchedulerJobService;
import com.stone.it.rcms.scheduler.vo.QuartzJobVO;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author cj.stone
 * @Date 2023/7/27
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
