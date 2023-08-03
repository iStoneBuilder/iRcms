package com.stone.it.micro.rcms.scheduler.service.impl;

import com.stone.it.micro.rcms.framecom.vo.PageResult;
import com.stone.it.micro.rcms.framecom.vo.PageVO;
import com.stone.it.micro.rcms.scheduler.dao.ISchedulerJobDao;
import com.stone.it.micro.rcms.scheduler.service.ISchedulerJobService;
import com.stone.it.micro.rcms.scheduler.vo.QuartzJobVO;
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
