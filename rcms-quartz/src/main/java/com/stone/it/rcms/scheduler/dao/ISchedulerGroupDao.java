package com.stone.it.rcms.scheduler.dao;

import com.stone.it.rcms.com.vo.PageResult;
import com.stone.it.rcms.com.vo.PageVO;
import com.stone.it.rcms.scheduler.vo.QuartzGroupVO;
import java.util.List;

/**
 *
 * @author cj.stone
 * @Date 2023/7/27
 * @Desc
 */
public interface ISchedulerGroupDao {

  PageResult<QuartzGroupVO> findQuartzGroupPageResult(QuartzGroupVO schedulerVO, PageVO pageVO);
  
  List<QuartzGroupVO> findQuartzGroupList(QuartzGroupVO schedulerVO);

  int createQuartzGroup(QuartzGroupVO schedulerVO);

  int deleteQuartzGroup(String jobId);

  int updateQuartzGroup(QuartzGroupVO schedulerVO);
  
}
