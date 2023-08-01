package com.stone.it.micro.rcms.scheduler.service.impl;

import com.stone.it.micro.rcms.framecom.vo.PageResult;
import com.stone.it.micro.rcms.framecom.vo.PageVO;
import com.stone.it.micro.rcms.scheduler.dao.ISchedulerGroupDao;
import com.stone.it.micro.rcms.scheduler.service.ISchedulerGroupService;
import com.stone.it.micro.rcms.scheduler.vo.QuartzGroupVO;
import javax.inject.Inject;
import javax.inject.Named;
import org.quartz.SchedulerException;


/**
 *
 * @author cj.stone
 * @Date 2023/7/27
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
