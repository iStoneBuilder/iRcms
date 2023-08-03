package com.stone.it.micro.rcms.scheduler.dao;

import com.stone.it.micro.rcms.framecom.vo.PageResult;
import com.stone.it.micro.rcms.framecom.vo.PageVO;
import com.stone.it.micro.rcms.scheduler.vo.QuartzGroupVO;
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
