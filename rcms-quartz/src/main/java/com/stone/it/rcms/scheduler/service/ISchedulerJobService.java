package com.stone.it.rcms.scheduler.service;

import com.stone.it.rcms.com.vo.PageResult;
import com.stone.it.rcms.com.vo.PageVO;
import com.stone.it.rcms.scheduler.vo.QuartzJobVO;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

/**
 * 查询任务执行记录
 *
 * @author cj.stone
 * @Date 2023/7/27
 * @Desc
 */
@Path("/quzrtz/job")
@Produces("application/json")
@Consumes("application/json")
public interface ISchedulerJobService {

  /**
   *  分页查询
   * @param quartzJobVO
   * @param pageVO
   * @return
   */
  @GET
  @Path("/records/page/{curPage}/{pageSize}")
  PageResult<QuartzJobVO> findQuartzJobPageResult(@QueryParam("") QuartzJobVO quartzJobVO,@PathParam("") PageVO pageVO);


}
