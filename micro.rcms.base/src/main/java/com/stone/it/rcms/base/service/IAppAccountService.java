package com.stone.it.rcms.base.service;

import com.alibaba.fastjson2.JSONObject;
import com.stone.it.rcms.base.vo.AppAccountVO;
import com.stone.it.rcms.core.annotate.RcmsMethodName;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
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
public interface IAppAccountService {

    /**
     * 新增应用账户
     *
     * @param appAccountVO
     * @return
     */
    @POST
    @Path("/records")
    @RcmsMethodName("新增账户")
    @RequiresPermissions("permission:account:create")
    JSONObject createAppAccount(AppAccountVO appAccountVO);

    /**
     * 删除应用账户
     *
     * @param accountCode
     * @return
     */
    @DELETE
    @Path("/records/{account_code}")
    @RcmsMethodName("删除账户")
    @RequiresPermissions("permission:account:delete")
    JSONObject deleteAppAccount(@PathParam("account_code") String accountCode);

    /**
     * 更新应用账户
     *
     * @param appAccountVO
     * @return
     */
    @PATCH
    @Path("/records/{account_code}")
    @RcmsMethodName("更新账户")
    @RequiresPermissions("permission:account:update")
    JSONObject updateAppAccount(@PathParam("account_code") String accountCode, AppAccountVO appAccountVO);

}
