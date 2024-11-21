package com.stone.it.rcms.base.service;

import com.stone.it.rcms.base.vo.RoleVO;
import com.stone.it.rcms.core.annotate.RcmsMethod;
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
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;

/**
 * 角色管理
 * 
 * @author cj.stone
 * @Desc
 */
@Path("/role")
@Produces("application/json")
@Consumes("application/json")
@RequiresAuthentication
public interface IRoleService {

    /**
     * 角色列表
     *
     * @return
     */
    @GET
    @Path("/records")
    @RcmsMethod(name = "角色管理.列表查询")
    @RequiresPermissions("permission:role:list:query")
    List<RoleVO> findRoleList(@QueryParam("") RoleVO roleVO);

    /**
     * 角色列表
     *
     * @return
     */
    @GET
    @Path("/records/list")
    @RcmsMethod(name = "角色管理.下级查询")
    @RequiresPermissions("permission:role:list2:query")
    List<RoleVO> findEnterPriseRoleList(@QueryParam("") RoleVO roleVO);

    /**
     * 查询企业(商户)列表
     *
     * @return
     */
    @GET
    @Path("/records/tree")
    @RcmsMethod(name = "角色管理.Tree查询")
    @RequiresPermissions("permission:role:tree:query")
    List<RoleVO> findRoleTree(@QueryParam("") RoleVO roleVO);

    /**
     * 创建角色
     *
     * @param roleVO
     * @return
     */
    @POST
    @Path("/records")
    @RcmsMethod(name = "角色管理.创建")
    @RequiresPermissions("permission:role:create")
    int createRole(RoleVO roleVO);

    /**
     * 更新角色
     *
     * @param roleId
     * @param roleVO
     * @return
     */
    @PUT
    @Path("/records/{role_id}")
    @RcmsMethod(name = "角色管理.更新")
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
    @RcmsMethod(name = "角色管理.删除")
    @RequiresPermissions("permission:role:delete")
    int deleteRole(@PathParam("role_id") String roleId);

}
