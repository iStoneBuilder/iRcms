package com.stone.it.rcms.scheduler.dao;

import com.stone.it.rcms.core.vo.PageResult;
import com.stone.it.rcms.core.vo.PageVO;
import com.stone.it.rcms.scheduler.vo.QuartzGroupVO;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 *
 * @author cj.stone
 * @Desc
 */
public interface ISchedulerGroupDao {

    PageResult<QuartzGroupVO> findQuartzGroupPageResult(QuartzGroupVO schedulerVO, PageVO pageVO);

    List<QuartzGroupVO> findQuartzGroupList(QuartzGroupVO quartzGroupVO);

    int createQuartzGroup(QuartzGroupVO schedulerVO);

    int deleteQuartzGroup(@Param("groupId") String groupId);

    int updateQuartzGroup(QuartzGroupVO schedulerVO);

    QuartzGroupVO findQuartzGroupInfoByCode(@Param("quartzGroupCode") String quartzGroupCode);

    QuartzGroupVO findQuartzGroupInfoById(@Param("quartzGroupId") String quartzGroupId);

    List<QuartzGroupVO> checkGroupCodeExistByCode(QuartzGroupVO schedulerVO);

    List<QuartzGroupVO> checkGroupNameExistByCodeName(QuartzGroupVO schedulerVO);

}
