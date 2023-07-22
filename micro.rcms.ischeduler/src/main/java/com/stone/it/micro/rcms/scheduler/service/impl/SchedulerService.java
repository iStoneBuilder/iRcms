package com.stone.it.micro.rcms.scheduler.service.impl;

import com.stone.it.micro.rcms.common.utils.UUIDUtil;
import com.stone.it.micro.rcms.framecom.vo.PageResult;
import com.stone.it.micro.rcms.framecom.vo.PageVO;
import com.stone.it.micro.rcms.scheduler.dao.ISchedulerDao;
import com.stone.it.micro.rcms.scheduler.manager.QuartzManager;
import com.stone.it.micro.rcms.scheduler.service.ISchedulerService;
import com.stone.it.micro.rcms.scheduler.vo.SchedulerVO;
import javax.annotation.Resource;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author cj.stone
 * @Date 2023/7/21
 * @Desc
 */
@Named
public class SchedulerService implements ISchedulerService {

  @Resource
  private QuartzManager quartzManager;

  @Inject
  private ISchedulerDao schedulerDao;

  @Override
  public PageResult<SchedulerVO> findQuartzPageResult(SchedulerVO schedulerVO, PageVO pageVO) {
    return schedulerDao.findQuartzPageResult(schedulerVO,pageVO);
  }

  @Override
  public int createQuartz(SchedulerVO schedulerVO) {
    // 设置任务ID
    schedulerVO.setQuartzId(UUIDUtil.getUuid());
    // 创建数据
    schedulerDao.createQuartz(schedulerVO);

    return 1;
  }

  @Override
  public int deleteQuartz(String jobId) {
    return schedulerDao.deleteQuartz(jobId);
  }

  @Override
  public int updateQuartz(SchedulerVO schedulerVO) {
    return schedulerDao.updateQuartz(schedulerVO);
  }

}
