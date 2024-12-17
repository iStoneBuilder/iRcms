package com.stone.it.rcms.user.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * 提供用户注册/登录等业务
 * 
 * @author cj.stone
 * @Date 2024/11/18
 * @Desc
 */

@Path("/user")
@Produces({MediaType.APPLICATION_JSON})
@Consumes(MediaType.APPLICATION_JSON)
public interface IUserService {}
