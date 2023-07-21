package com.stone.it.micro.rcms.framebase.service;

import com.stone.it.micro.rcms.framebase.vo.UserVO;
import com.stone.it.micro.rcms.framecom.vo.PageResult;
import com.stone.it.micro.rcms.framecom.vo.PageVO;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PATCH;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

/**
 * @author cj.stone
 * @Date 2023/4/26
 * @Desc
 */
@Path("/user")
@Produces("application/json")
@Consumes("application/json")
public interface IUserService {

  /**
   * 分页查询
   *
   * @param userVO
   * @param pageVO
   * @return
   */
  @GET
  @Path("/records/page/{curPage}/{pageSize}")
  PageResult<UserVO> findUserPageResult(@QueryParam("") UserVO userVO,
      @PathParam("") PageVO pageVO);


  /**
   * 详情
   *
   * @param userId
   * @return
   */
  @GET
  @Path("/records/{user_id}")
  UserVO findUserById(@PathParam("user_id")String userId);

  /**
   * 创建
   *
   * @param userVO
   * @return
   */
  @POST
  @Path("/records")
  int createUser(UserVO userVO);

  /**
   * 更新
   *
   * @param userId
   * @param userVO
   * @return
   */
  @PATCH
  @Path("/records/{user_id}")
  int updateUser(@PathParam("user_id")String userId, UserVO userVO);

  /**
   * 删除
   *
   * @param userId
   * @return
   */
  @DELETE
  @Path("/records/{user_id}")
  int deleteUser(@PathParam("user_id")String userId);

}
