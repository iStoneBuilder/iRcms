package com.stone.it.rcms.base.service;

import com.stone.it.rcms.base.vo.PermissionVO;
import com.stone.it.rcms.core.vo.PageResult;
import com.stone.it.rcms.core.vo.PageVO;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import org.apache.shiro.authz.annotation.RequiresPermissions;

/**
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
    @RequiresPermissions("permission:permission:page:query")
    PageResult<PermissionVO> findI18nPageResult(@QueryParam("") PermissionVO permissionVO,
        @PathParam("") PageVO pageVO);

}
