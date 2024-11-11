package com.stone.it.rcms.base.service;

import com.alibaba.fastjson2.JSONObject;
import com.stone.it.rcms.base.vo.AccountVO;
import com.stone.it.rcms.core.annotate.RcmsMethod;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PATCH;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
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
   * 获取应用账户列表
   *
   * @param accountVO
   * @return
   */
  @GET
  @Path("/records")
  @RcmsMethod(name = "获取账户列表")
  @RequiresPermissions("permission:account:list:query")
  List<AccountVO> getAccountList(@PathParam("accountVO") AccountVO accountVO);

  /**
   * 新增应用账户
   *
   * @param accountVO
   * @return
   */
  @POST
  @Path("/records")
  @RcmsMethod(name = "新增账户")
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
  @RcmsMethod(name = "删除账户")
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
  @RcmsMethod(name = "更新账户")
  @RequiresPermissions("permission:account:update")
  int updateAccount(@PathParam("code") String code, AccountVO accountVO);

}
