package com.stone.it.rcms.scheduler.dao;

import com.stone.it.rcms.core.vo.PageResult;
import com.stone.it.rcms.core.vo.PageVO;
import com.stone.it.rcms.scheduler.vo.QuartzGroupVO;
import com.stone.it.rcms.scheduler.vo.SchedulerVO;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 *
 * @author cj.stone
 * @Desc
 */
public interface ISchedulerGroupDao {

  PageResult<QuartzGroupVO> findQuartzGroupPageResult(QuartzGroupVO schedulerVO, PageVO pageVO);
  
  List<QuartzGroupVO> findQuartzGroupList(QuartzGroupVO schedulerVO);

  int createQuartzGroup(QuartzGroupVO schedulerVO);

  int deleteQuartzGroup(String jobId);

  int updateQuartzGroup(QuartzGroupVO schedulerVO);

  SchedulerVO findQuartzGroupInfo(@Param("quartzGroupCode")String quartzGroupCode);
}
