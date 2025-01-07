package com.stone.it.rcms.scheduler.dao;

import com.stone.it.rcms.core.vo.PageResult;
import com.stone.it.rcms.core.vo.PageVO;
import com.stone.it.rcms.scheduler.vo.QuartzJobVO;

/**
 *  任务执行记录
 * @author cj.stone
 * @Desc
 */
public interface ISchedulerJobDao {

  /** 分页查询 **/
  PageResult<QuartzJobVO> findQuartzJobPageResult(QuartzJobVO quartzJobVO, PageVO pageVO);

  /** 创建任务 **/
  int createJob(QuartzJobVO quartzJobVO);

  /** 更新任务 **/
  int updateJob(QuartzJobVO quartzJobVO);
}
