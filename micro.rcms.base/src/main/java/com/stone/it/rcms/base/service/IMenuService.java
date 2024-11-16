package com.stone.it.rcms.base.service;

import com.stone.it.rcms.base.vo.MenuVO;
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
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;

/**
 * 栏目
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

  @GET
  @Path("/records/tree")
  @RcmsMethod(name = "栏目管理.Tree查询")
  @RequiresPermissions("permission:menu:tree:query")
  List<MenuVO> getMenuTreeList();

  @POST
  @Path("/records")
  @RcmsMethod(name = "栏目管理.新增")
  @RequiresPermissions("permission:menu:create")
  int createMenu(MenuVO menuVO);

  @PUT
  @Path("/records/{id}")
  @RcmsMethod(name = "栏目管理.新增")
  @RequiresPermissions("permission:menu:update")
  int updateMenu(@PathParam("id") String id, MenuVO menuVO);

  @DELETE
  @Path("/records/{id}")
  @RcmsMethod(name = "栏目管理.删除")
  @RequiresPermissions("permission:menu:update")
  int deleteMenu(@PathParam("id") String id);

}