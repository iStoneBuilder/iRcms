package com.stone.it.rcms.base.service;

import com.stone.it.rcms.base.vo.UserManageVO;
import com.stone.it.rcms.core.vo.PageResult;
import com.stone.it.rcms.core.vo.PageVO;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import org.apache.shiro.authz.annotation.RequiresAuthentication;

/**
 * @author cj.stone
 * @Desc
 */
@Path("/user")
@Produces("application/json")
@Consumes("application/json")
@RequiresAuthentication
public interface IUserManageService {

    /**
     * 分页查询
     */
    @GET
    @Path("/records/page/{curPage}/{pageSize}")
    PageResult<UserManageVO> findUserPageResult(@QueryParam("") UserManageVO userVO, @PathParam("") PageVO pageVO);

    /**
     * 详情
     */
    @GET
    @Path("/records/{user_id}")
    UserManageVO findUserById(@PathParam("user_id") String userId);

}
