package com.stone.it.rcms.web.support.service;

import com.stone.it.rcms.core.annotate.RcmsMethod;
import com.stone.it.rcms.web.support.vo.HomeVO;
import org.apache.shiro.authz.annotation.RequiresAuthentication;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author cj.stone
 * @Date 2024/12/13
 * @Desc
 */
@Path("/home")
@Produces({MediaType.APPLICATION_JSON})
@Consumes(MediaType.APPLICATION_JSON)
@RequiresAuthentication
public interface IHomeService {

    @GET
    @Path("/records")
    @RcmsMethod(name = "主页数据.登录查询")
    HomeVO getHomeData();

}
