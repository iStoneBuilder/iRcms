package com.stone.it.micro.rcms.scheduler.dao;

import com.stone.it.micro.rcms.scheduler.vo.SchedulerVO;
import java.util.List;

/**
 *
 * @author cj.stone
 * @Date 2023/7/19
 * @Desc
 */
public interface ISchedulerDao {

  /**
   * 查询所有配置的任务
   *
   * @param schedulerVO
   * @return
   */
  List<SchedulerVO> findJobList(SchedulerVO schedulerVO);
}
