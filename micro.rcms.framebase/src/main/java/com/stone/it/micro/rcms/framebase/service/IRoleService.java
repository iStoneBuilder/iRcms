package com.stone.it.micro.rcms.framebase.service;

import com.stone.it.micro.rcms.framebase.vo.RoleVO;
import com.stone.it.micro.rcms.framecore.vo.PageVO;
import com.stone.it.micro.rcms.framecore.vo.PagedResult;
import com.stone.it.micro.rcms.framecore.vo.ResultVO;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PATCH;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

/**
 * @author cj.stone
 * @Date 2023/4/26
 * @Desc
 */
@Path("/role")
@Produces("application/json")
@Consumes("application/json")
public interface IRoleService {

  /**
   * 分页查询
   *
   * @param roleVO
   * @param pageVO
   * @return
   */
  @GET
  @Path("/records/page/{curPage}/{pageSize}")
  PagedResult<RoleVO> findRolePageResult(@QueryParam("") RoleVO roleVO,
      @PathParam("") PageVO pageVO);
  

  /**
   * 详情
   *
   * @param roleId
   * @return
   */
  @GET
  @Path("/records/{role_id}")
  RoleVO findRoleById(@PathParam("role_id")String roleId);

  /**
   * 创建
   *
   * @param roleVO
   * @return
   */
  @POST
  @Path("/records")
  int createRole(RoleVO roleVO);

  /**
   * 更新
   *
   * @param roleId
   * @param roleVO
   * @return
   */
  @PATCH
  @Path("/records/{role_id}")
  int updateRole(@PathParam("role_id")String roleId, RoleVO roleVO);

  /**
   * 删除
   *
   * @param roleId
   * @return
   */
  @DELETE
  @Path("/records/{role_id}")
  int deleteRole(@PathParam("role_id")String roleId);
  
}
