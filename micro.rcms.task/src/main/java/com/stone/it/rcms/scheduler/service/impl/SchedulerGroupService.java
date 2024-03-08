package com.stone.it.rcms.scheduler.service.impl;

import com.stone.it.rcms.core.vo.PageResult;
import com.stone.it.rcms.core.vo.PageVO;
import com.stone.it.rcms.scheduler.dao.ISchedulerGroupDao;
import com.stone.it.rcms.scheduler.service.ISchedulerGroupService;
import com.stone.it.rcms.scheduler.vo.QuartzGroupVO;
import javax.inject.Inject;
import javax.inject.Named;
import org.quartz.SchedulerException;


/**
 *
 * @author cj.stone
 * @Desc
 */
@Named
public class SchedulerGroupService implements ISchedulerGroupService {

  @Inject
  private ISchedulerGroupDao schedulerGroupDao;

  @Override
  public PageResult<QuartzGroupVO> findQuartzGroupPageResult(QuartzGroupVO schedulerVO, PageVO pageVO) {
    return schedulerGroupDao.findQuartzGroupPageResult(schedulerVO,pageVO);
  }

  @Override
  public QuartzGroupVO createQuartzGroup(QuartzGroupVO schedulerVO) throws Exception {
    schedulerGroupDao.createQuartzGroup(schedulerVO);
    return schedulerVO;
  }

  @Override
  public int deleteQuartzGroup(String groupId) throws SchedulerException {
    return schedulerGroupDao.deleteQuartzGroup(groupId);
  }

  @Override
  public QuartzGroupVO updateQuartzGroup(String groupId, QuartzGroupVO schedulerVO) throws Exception {
    schedulerVO.setQuartzGroupId(groupId);
    schedulerGroupDao.updateQuartzGroup(schedulerVO);
    return schedulerVO;
  }
}
