package com.stone.it.rcms.base.service;

import com.stone.it.rcms.core.annotate.RcmsMethod;
import com.stone.it.rcms.core.vo.PageResult;
import com.stone.it.rcms.core.vo.PageVO;
import com.stone.it.rcms.core.vo.PermissionVO;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import org.apache.shiro.authz.annotation.RequiresPermissions;

/**
 * 接口权限管理
 * 
 * @author cj.stone
 * @Date 2024/11/1
 * @Desc
 */
@Path("/permission")
@Produces({MediaType.APPLICATION_JSON})
@Consumes(MediaType.APPLICATION_JSON)
public interface IPermissionService {

    @GET
    @Path("/records/page/{curPage}/{pageSize}")
    @RcmsMethod(name = "接口管理.分页查询")
    @RequiresPermissions("permission:permission:page:query")
    PageResult<PermissionVO> findPermissionPageResult(@QueryParam("") PermissionVO permissionVO,
        @PathParam("") PageVO pageVO);

    @POST
    @Path("/records/refresh")
    @RcmsMethod(name = "接口管理.刷新")
    // @RequiresPermissions("permission:permission:refresh")
    int refreshPermission(PermissionVO permissionVO);

}
