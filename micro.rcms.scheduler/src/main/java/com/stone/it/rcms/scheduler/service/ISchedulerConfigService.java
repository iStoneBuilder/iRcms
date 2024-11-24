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
     * 分页查询
     *
     * @param schedulerVO a
     * @param pageVO a
     * @return a
     */
    @GET
    @Path("/records/page/{curPage}/{pageSize}")
    PageResult<SchedulerVO> findQuartzPageResult(@QueryParam("") SchedulerVO schedulerVO, @PathParam("") PageVO pageVO);

    /**
     * 创建任务
     *
     * @param schedulerVO a
     * @return a
     * @throws Exception a
     */
    @POST
    @Path("/records")
    SchedulerVO createQuartz(SchedulerVO schedulerVO) throws Exception;

    /**
     * 删除任务
     *
     * @param quartzId a
     * @return a
     * @throws SchedulerException a
     */
    @DELETE
    @Path("/records/{quartz_id}")
    int deleteQuartz(@PathParam("quartz_id") String quartzId) throws SchedulerException;

    /**
     * 更新任务（未启用的才允许更新）
     *
     * @param schedulerVO a
     * @return a
     * @throws Exception a
     */
    @PUT
    @Path("/records/{quartz_id}")
    SchedulerVO updateQuartz(@PathParam("quartz_id") String quartzId, SchedulerVO schedulerVO) throws Exception;

    /**
     * 操作任务（enable:启用; suspend:暂停; restore:恢复; stopped: 停用）
     *
     * @param quartzId a
     * @param operate a
     * @return a
     */
    @POST
    @Path("/records/{operate}/{quartz_id}")
    SchedulerVO operateQuartz(@PathParam("quartz_id") String quartzId, @PathParam("operate") String operate)
        throws Exception;

}
