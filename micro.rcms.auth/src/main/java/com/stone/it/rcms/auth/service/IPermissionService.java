package com.stone.it.rcms.auth.service;

import com.stone.it.rcms.auth.vo.PermissionVO;
import com.stone.it.rcms.core.vo.PageResult;
import com.stone.it.rcms.core.vo.PageVO;
import java.util.List;
import java.util.Set;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PATCH;
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
    PermissionVO findPermissionById(@PathParam("permission_id") String permission_id);

    /**
     * 查询已存在的服务信息
     * 
     * @param apiPaths
     * @return
     */
    List<PermissionVO> findPermissionByPaths(Set<String> apiPaths);

    /**
     * 创建新增的权限路径
     * 
     * @param permissionList
     */
    void createPermission(List<PermissionVO> permissionList);

    /**
     * 删除不存在的权限信息
     * 
     * @param permissionPathSet
     */
    void deletePermissionNotInList(Set<String> permissionPathSet);

    /**
     * 清理不存在授权关系
     * 
     */
    void deletePermissionRelation();
}
