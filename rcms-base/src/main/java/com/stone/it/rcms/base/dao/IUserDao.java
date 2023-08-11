package com.stone.it.rcms.base.dao;


import com.stone.it.rcms.base.vo.UserVO;
import com.stone.it.rcms.com.vo.PageVO;

/**
 * @author cj.stone
 * @Date 2023/4/26
 * @Desc
 */
public interface IUserDao {

  PageResult<UserVO> findPageResult(UserVO UserVO, PageVO pageVO);

  UserVO findUserId(String userId);

  int createUser(UserVO UserVO);

  int updateUser(UserVO UserVO);

  int deleteUser(String userId);

}
