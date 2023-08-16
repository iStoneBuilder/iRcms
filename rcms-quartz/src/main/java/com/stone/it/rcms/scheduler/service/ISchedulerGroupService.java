package com.stone.it.rcms.scheduler.service;

import com.stone.it.rcms.com.vo.PageResult;
import com.stone.it.rcms.com.vo.PageVO;
import com.stone.it.rcms.scheduler.vo.QuartzGroupVO;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PATCH;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import org.quartz.SchedulerException;

/**
 *  创建任务分组
 *
 * @author cj.stone
 * @Date 2023/7/27
 * @Desc
 */
@Path("/quzrtz/group")
@Produces("application/json")
@Consumes("application/json")
public interface ISchedulerGroupService {


  /**
   *  分页查询
   *
   * @param schedulerVO
   * @param pageVO
   * @return
   */
  @GET
  @Path("/records/page/{curPage}/{pageSize}")
  PageResult<QuartzGroupVO> findQuartzGroupPageResult(@QueryParam("") QuartzGroupVO schedulerVO, @PathParam("") PageVO pageVO);

  /**
   *  创建任务
   *
   * @param schedulerVO
   * @return
   * @throws Exception
   */
  @POST
  @Path("/records")
  QuartzGroupVO createQuartzGroup(QuartzGroupVO schedulerVO) throws Exception;

  /**
   * 删除任务
   *
   * @param groupId
   * @return
   * @throws SchedulerException
   */
  @DELETE
  @Path("/records/{groupId}")
  int deleteQuartzGroup(@PathParam("groupId") String groupId) throws SchedulerException;

  /**
   * 更新任务（未启用的才允许更新）
   *
   * @param schedulerVO
   * @return
   * @throws Exception
   */
  @PATCH
  @Path("/records/{groupId}")
  QuartzGroupVO updateQuartzGroup(@PathParam("groupId") String groupId,QuartzGroupVO schedulerVO) throws Exception;

}
