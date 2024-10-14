package com.stone.it.rcms.scheduler.service;


import com.stone.it.rcms.core.vo.PageResult;
import com.stone.it.rcms.core.vo.PageVO;
import com.stone.it.rcms.scheduler.vo.*;
import javax.ws.rs.*;
import org.quartz.SchedulerException;

/**
 * 创建定时任务
 *
 * @author cj.stone
 * @Desc
 */
@Path("/task")
@Produces("application/json")
@Consumes("application/json")
public interface ISchedulerConfigService {

  /**
   *  分页查询
   *
   * @param schedulerVO
   * @param pageVO
   * @return
   */
  @GET
  @Path("/records/page/{curPage}/{pageSize}")
  PageResult<SchedulerVO> findQuartzPageResult(@QueryParam("") SchedulerVO schedulerVO, @PathParam("") PageVO pageVO);

  /**
   *  创建任务
   *
   * @param schedulerVO
   * @return
   * @throws Exception
   */
  @POST
  @Path("/records")
  SchedulerVO createQuartz(SchedulerVO schedulerVO) throws Exception;

  /**
   * 删除任务
   *
   * @param quartzId
   * @return
   * @throws SchedulerException
   */
  @DELETE
  @Path("/records/{quartzId}")
  int deleteQuartz(@PathParam("quartzId") String quartzId) throws SchedulerException;

  /**
   * 更新任务（未启用的才允许更新）
   *
   * @param schedulerVO
   * @return
   * @throws Exception
   */
  @PATCH
  @Path("/records/{quartzId}")
  SchedulerVO updateQuartz(@PathParam("quartzId") String quartzId,SchedulerVO schedulerVO) throws Exception;

  /**
   *  操作任务（enable:启用; suspend:暂停; restore:恢复; stopped: 停用）
   *
   * @param quartzId
   * @param operate
   * @return
   */
  @POST
  @Path("/records/{operate}/{quartzId}")
  SchedulerVO operateQuartz(@PathParam("quartzId") String quartzId,@PathParam("operate") String operate)
      throws Exception;

}
