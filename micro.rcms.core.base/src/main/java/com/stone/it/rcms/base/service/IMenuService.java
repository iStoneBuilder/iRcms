package com.stone.it.rcms.base.service;

import com.alibaba.fastjson2.JSONObject;
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
import javax.ws.rs.core.MediaType;

import com.stone.it.rcms.core.vo.MenuVO;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;

/**
 * 菜单栏目
 *
 * @author cj.stone
 * @Desc
 */
@Path("/menu")
@Produces({MediaType.APPLICATION_JSON})
@Consumes(MediaType.APPLICATION_JSON)
@RequiresAuthentication
public interface IMenuService {

    @GET
    @Path("/records")
    @RcmsMethod(name = "栏目管理.列表查询")
    @RequiresPermissions("permission:menu:list:query")
    List<MenuVO> getMenuList(@QueryParam("") MenuVO menuVO);

    @POST
    @Path("/records")
    @RcmsMethod(name = "栏目管理.新增")
    @RequiresPermissions("permission:menu:create")
    int createMenu(MenuVO menuVO);

    @PUT
    @Path("/records/{id}")
    @RcmsMethod(name = "栏目管理.更新")
    @RequiresPermissions("permission:menu:update")
    int updateMenu(@PathParam("id") String id, MenuVO menuVO);

    @DELETE
    @Path("/records/{id}")
    @RcmsMethod(name = "栏目管理.删除")
    @RequiresPermissions("permission:menu:delete")
    int deleteMenu(@PathParam("id") String id);

}
