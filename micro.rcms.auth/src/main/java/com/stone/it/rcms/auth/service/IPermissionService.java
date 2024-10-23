package com.stone.it.rcms.auth.service;

import com.stone.it.rcms.auth.vo.PermissionVO;
import com.stone.it.rcms.core.vo.PageResult;
import com.stone.it.rcms.core.vo.PageVO;
import java.util.List;
import java.util.Set;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PATCH;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * 权限服务接口
 * 
 * @author cj.stone
 * @Date 2024/10/23
 * @Desc
 */
@Path("")
@Produces({MediaType.APPLICATION_JSON})
@Consumes(MediaType.APPLICATION_JSON)
public interface IPermissionService {

    @GET
    @Path("/records/page/{curPage}/{pageSize}")
    PageResult<PermissionVO> findI18nPageResult(@QueryParam("") PermissionVO permissionVO,
        @PathParam("") PageVO pageVO);

    @PATCH
    @Path("/records/{permission_id}")
    int updatePermission(@PathParam("permission_id") String permission_id, PermissionVO permissionVO);

    @GET
    @Path("/records/{permission_id}")
    PermissionVO getPermission(@PathParam("permission_id") String permission_id);

    List<PermissionVO> getPermissionByPaths(Set<String> apiPaths);

    void createPermission(List<PermissionVO> permissionList);
}
