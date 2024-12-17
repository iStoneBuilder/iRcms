package com.stone.it.rcms.user.service;

import com.alibaba.fastjson2.JSONObject;
import com.stone.it.rcms.core.annotate.RcmsMethod;
import com.stone.it.rcms.user.vo.AccountSecretVO;
import com.stone.it.rcms.user.vo.AppSecretVO;
import com.stone.it.rcms.user.vo.LoginResponseVO;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author cj.stone
 * @Date 2024/10/14
 * @Desc
 */
@Path("/")
@Produces({MediaType.APPLICATION_JSON})
@Consumes(MediaType.APPLICATION_JSON)
public interface ILoginService {

    @POST
    @Path("login")
    @RcmsMethod(name = "账户管理.登录")
    LoginResponseVO accountLogin(AccountSecretVO accountSecretVO);

    @POST
    @Path("refresh/login")
    @RcmsMethod(name = "账户管理.登录刷新")
    LoginResponseVO accountLoginRefresh(LoginResponseVO loginResVO);

    @POST
    @Path("logout")
    @RcmsMethod(name = "账户管理.退出")
    JSONObject accountLogout();

    @POST
    @Path("token")
    @RcmsMethod(name = "应用管理.登录")
    JSONObject appToken(AppSecretVO appSecretVO);

}
