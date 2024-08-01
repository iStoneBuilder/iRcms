package com.stone.it.rcms.scheduler.service.impl;

import com.stone.it.rcms.core.util.UUIDUtil;
import com.stone.it.rcms.core.vo.PageResult;
import com.stone.it.rcms.core.vo.PageVO;
import com.stone.it.rcms.scheduler.dao.ISchedulerConfigDao;
import com.stone.it.rcms.scheduler.dao.ISchedulerGroupDao;
import com.stone.it.rcms.scheduler.service.ISchedulerGroupService;
import com.stone.it.rcms.scheduler.vo.QuartzGroupVO;
import com.stone.it.rcms.scheduler.vo.SchedulerVO;
import java.util.List;
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

  @Inject
  private ISchedulerConfigDao schedulerConfigDao;;

  @Override
  public PageResult<QuartzGroupVO> findQuartzGroupPageResult(QuartzGroupVO schedulerVO, PageVO pageVO) {
    return schedulerGroupDao.findQuartzGroupPageResult(schedulerVO,pageVO);
  }

  @Override
  public QuartzGroupVO createQuartzGroup(QuartzGroupVO quartzGroupVO) throws Exception {
    quartzGroupVO.setQuartzGroupId(UUIDUtil.getUuid());
    schedulerGroupDao.createQuartzGroup(quartzGroupVO);
    return quartzGroupVO;
  }

  @Override
  public int deleteQuartzGroup(String groupId) throws SchedulerException {
    // 判断任务组是否存在任务
    List<SchedulerVO> list = schedulerConfigDao.findQuartzListByGroupId(
      groupId);
    if(list.size()>0){
      throw  new SchedulerException("任务组存在任务数据，请先删除任务组下的数据！");
    }
    return schedulerGroupDao.deleteQuartzGroup(groupId);
  }

  @Override
  public QuartzGroupVO updateQuartzGroup(String groupId, QuartzGroupVO quartzGroupVO) throws SchedulerException {
    // 判断是否更改了任务组认证
    QuartzGroupVO oldVO = schedulerGroupDao.findQuartzGroupInfoById(groupId);
    if(!oldVO.getIsAuthorized().equals(quartzGroupVO.getIsAuthorized())){
      // 判断任务组下任务是否存在启动的任务
      List<SchedulerVO> list = schedulerConfigDao.findQuartzListByGroupId(
        groupId);
      for (SchedulerVO schedulerVO : list) {
        if("enable".equals(schedulerVO.getEnabledFlag())){
          throw new SchedulerException("更改任务组认证启停：需要停止任务组下已启动的任务！");
        }
      }
    }
    quartzGroupVO.setQuartzGroupId(groupId);
    schedulerGroupDao.updateQuartzGroup(quartzGroupVO);
    return quartzGroupVO;
  }

  @Override
  public List<QuartzGroupVO> findQuartzGroupList(QuartzGroupVO quartzGroupVO)
    throws SchedulerException {
    return schedulerGroupDao.findQuartzGroupList(quartzGroupVO);
  }
}
