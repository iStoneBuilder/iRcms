package com.stone.it.rcms.base.service;

import com.stone.it.rcms.base.vo.AccountVO;
import com.stone.it.rcms.core.annotate.RcmsMethod;
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
import javax.ws.rs.core.MediaType;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;

/**
 * 应用账户,企业账户管理
 *
 * @author cj.stone
 * @Date 2024/11/2
 * @Desc
 */
@Path("/account")
@Produces({MediaType.APPLICATION_JSON})
@Consumes(MediaType.APPLICATION_JSON)
@RequiresAuthentication
public interface IAccountService {


  /**
   * 获取应用账户分页列表
   *
   * @param accountVO
   * @return
   */
  @GET
  @Path("/records/page/{curPage}/{pageSize}")
  @RcmsMethod(name = "账户管理.分页查询")
  @RequiresPermissions("permission:account:page:query")
  PageResult<AccountVO> findAccountPageResult(@QueryParam("") AccountVO accountVO,
    @PathParam("") PageVO pageVO);

  /**
   * 获取应用账户列表
   *
   * @param accountVO
   * @return
   */
  @GET
  @Path("/records")
  @RcmsMethod(name = "账户管理.列表查询")
  @RequiresPermissions("permission:account:list:query")
  List<AccountVO> findAccountList(@PathParam("accountVO") AccountVO accountVO);

  /**
   * 新增应用账户
   *
   * @param accountVO
   * @return
   */
  @POST
  @Path("/records")
  @RcmsMethod(name = "账户管理.新增")
  @RequiresPermissions("permission:account:create")
  int createAccount(AccountVO accountVO);

  /**
   * 删除应用账户
   *
   * @param code
   * @return
   */
  @DELETE
  @Path("/records/{code}")
  @RcmsMethod(name = "账户管理.删除")
  @RequiresPermissions("permission:account:delete")
  int deleteAccount(@PathParam("code") String code);

  /**
   * 更新应用账户
   *
   * @param accountVO
   * @return
   */
  @PATCH
  @Path("/records/{code}")
  @RcmsMethod(name = "账户管理.更新")
  @RequiresPermissions("permission:account:update")
  int updateAccount(@PathParam("code") String code, AccountVO accountVO);

}
