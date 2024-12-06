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

import com.stone.it.rcms.core.vo.PermissionVO;
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
     * 角色查询（包含商户下所有角色）
     */
    @GET
    @Path("/records")
    @RcmsMethod(name = "角色管理.角色查询")
    @RequiresPermissions("permission:role:list:query")
    List<RoleVO> findRoleList(@QueryParam("") RoleVO roleVO);

    /**
     * 商户角色查询
     */
    @GET
    @Path("/records/list")
    @RcmsMethod(name = "角色管理.商户角色查询")
    @RequiresPermissions("permission:role:e-list:query")
    List<RoleVO> findEnterPriseRoleList(@QueryParam("") RoleVO roleVO);

    /**
     * 角色权限查询
     */
    @GET
    @Path("/records/{role_id}/permissions")
    @RcmsMethod(name = "角色管理.角色权限查询")
    @RequiresPermissions("permission:role:permissions:query")
    List<PermissionVO> findRolePermissionList(@PathParam("role_id") String roleId);

    List<RoleVO> findRoleTree(RoleVO roleVO);

    /**
     * 创建角色
     */
    @POST
    @Path("/records/{role_id}/authorize")
    @RcmsMethod(name = "角色管理.授权")
    @RequiresPermissions("permission:role:authorize")
    int createRolePermission(@PathParam("role_id") String roleId, List<PermissionVO> permissionList);

    /**
     * 创建角色
     */
    @POST
    @Path("/records")
    @RcmsMethod(name = "角色管理.创建")
    @RequiresPermissions("permission:role:create")
    int createRole(RoleVO roleVO);

    /**
     * 更新角色
     *
     */
    @PUT
    @Path("/records/{role_id}")
    @RcmsMethod(name = "角色管理.更新")
    @RequiresPermissions("permission:role:update")
    int updateRole(@PathParam("role_id") String roleId, RoleVO roleVO);

    /**
     * 删除角色
     */
    @DELETE
    @Path("/records/{role_id}")
    @RcmsMethod(name = "角色管理.删除")
    @RequiresPermissions("permission:role:delete")
    int deleteRole(@PathParam("role_id") String roleId);

}
