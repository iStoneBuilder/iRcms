package com.stone.it.rcms.base.service;

import com.stone.it.rcms.base.vo.RoleVO;
import com.stone.it.rcms.core.vo.PageResult;
import com.stone.it.rcms.core.vo.PageVO;
import java.util.List;
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
import org.apache.shiro.authz.annotation.RequiresPermissions;

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
     * 分页查询角色列表
     *
     * @param roleVO
     * @param pageVO
     * @return
     */
    @GET
    @Path("/records/page/{curPage}/{pageSize}")
    @RequiresPermissions("permission:role:page:query")
    PageResult<RoleVO> findRolePageResult(@QueryParam("") RoleVO roleVO, @PathParam("") PageVO pageVO);

    /**
     * 角色列表
     *
     * @return
     */
    @GET
    @Path("/records/list/{parent_code}")
    @RequiresPermissions("permission:role:list:query")
    List<RoleVO> findRoleList(@PathParam("parent_code") String parent_code);

    /**
     * 角色详情
     *
     * @param roleId
     * @return
     */
    @GET
    @Path("/records/{role_id}")
    @RequiresPermissions("permission:role:query")
    RoleVO findRoleById(@PathParam("role_id") String roleId);

    /**
     * 创建角色
     *
     * @param roleVO
     * @return
     */
    @POST
    @Path("/records")
    @RequiresPermissions("permission:role:create")
    int createRole(RoleVO roleVO);

    /**
     * 更新角色
     *
     * @param roleId
     * @param roleVO
     * @return
     */
    @PATCH
    @Path("/records/{role_id}")
    @RequiresPermissions("permission:role:update")
    int updateRole(@PathParam("role_id") String roleId, RoleVO roleVO);

    /**
     * 删除角色
     *
     * @param roleId
     * @return
     */
    @DELETE
    @Path("/records/{role_id}")
    @RequiresPermissions("permission:role:delete")
    int deleteRole(@PathParam("role_id") String roleId);

}
