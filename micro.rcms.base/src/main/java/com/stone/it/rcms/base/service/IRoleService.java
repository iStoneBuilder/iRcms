package com.stone.it.rcms.base.service;

import com.stone.it.rcms.base.vo.RoleVO;
import com.stone.it.rcms.core.vo.PageResult;
import com.stone.it.rcms.core.vo.PageVO;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PATCH;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import org.apache.shiro.authz.annotation.RequiresAuthentication;

/**
 * @author cj.stone
 * @Desc
 */
@Path("/role")
@Produces("application/json")
@Consumes("application/json")
@RequiresAuthentication
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
    PageResult<RoleVO> findRolePageResult(@QueryParam("") RoleVO roleVO, @PathParam("") PageVO pageVO);

    /**
     * 详情
     *
     * @param roleId
     * @return
     */
    @GET
    @Path("/records/{role_id}")
    RoleVO findRoleById(@PathParam("role_id") String roleId);

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
    int updateRole(@PathParam("role_id") String roleId, RoleVO roleVO);

    /**
     * 删除
     *
     * @param roleId
     * @return
     */
    @DELETE
    @Path("/records/{role_id}")
    int deleteRole(@PathParam("role_id") String roleId);

}
