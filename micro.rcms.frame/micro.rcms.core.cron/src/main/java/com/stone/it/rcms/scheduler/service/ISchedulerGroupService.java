package com.stone.it.rcms.scheduler.service;

import com.stone.it.rcms.core.vo.PageResult;
import com.stone.it.rcms.core.vo.PageVO;
import com.stone.it.rcms.scheduler.vo.QuartzGroupVO;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import org.quartz.SchedulerException;

/**
 * 创建任务分组
 *
 * @author cj.stone
 * @Desc
 */
@Path("/group")
@Produces("application/json")
@Consumes("application/json")
public interface ISchedulerGroupService {

    /**
     * 分页查询
     *
     * @param schedulerVO schedulerVO
     * @param pageVO pageVO
     * @return PageResult<QuartzGroupVO>
     */
    @GET
    @Path("/records/page/{curPage}/{pageSize}")
    PageResult<QuartzGroupVO> findQuartzGroupPageResult(@QueryParam("") QuartzGroupVO schedulerVO,
        @PathParam("") PageVO pageVO);

    /**
     * 创建任务
     *
     * @param schedulerVO schedulerVO
     * @return QuartzGroupVO
     * @throws Exception e
     */
    @POST
    @Path("/records")
    QuartzGroupVO createQuartzGroup(QuartzGroupVO schedulerVO) throws Exception;

    /**
     * 删除任务
     *
     * @param groupId groupId
     * @return int
     * @throws SchedulerException schedulerException
     */
    @DELETE
    @Path("/records/{group_id}")
    int deleteQuartzGroup(@PathParam("group_id") String groupId) throws SchedulerException;

    /**
     * 更新任务（未启用的才允许更新）
     *
     * @param schedulerVO schedulerVO
     * @return QuartzGroupVO
     * @throws SchedulerException exception
     */
    @PUT
    @Path("/records/{group_id}")
    QuartzGroupVO updateQuartzGroup(@PathParam("group_id") String groupId, QuartzGroupVO schedulerVO)
        throws SchedulerException;

    /**
     * 查询任务组列表
     *
     * @param quartzGroupVO a
     * @return a
     * @throws SchedulerException a
     */
    @GET
    @Path("/records/list")
    List<QuartzGroupVO> findQuartzGroupList(@QueryParam("") QuartzGroupVO quartzGroupVO) throws SchedulerException;

}
