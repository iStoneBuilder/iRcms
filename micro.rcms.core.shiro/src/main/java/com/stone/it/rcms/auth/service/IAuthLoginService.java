package com.stone.it.rcms.auth.service;

import com.alibaba.fastjson2.JSONObject;
import com.stone.it.rcms.auth.vo.AccountSecretVO;
import com.stone.it.rcms.auth.vo.AppSecretVO;
import com.stone.it.rcms.auth.vo.LoginResponseVO;
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
public interface IAuthLoginService {

    @POST
    @Path("login")
    LoginResponseVO userLogin(AccountSecretVO accountSecretVO);

    @POST
    @Path("refresh/login")
    LoginResponseVO userLoginRefresh(LoginResponseVO loginResVO);

    @POST
    @Path("logout")
    JSONObject userLogout();

    @POST
    @Path("token")
    JSONObject appToken(AppSecretVO appSecretVO);

}
