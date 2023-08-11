package com.stone.it.rcms.base.service.impl;

import com.stone.it.rcms.com.util.UUIDUtil;
import com.stone.it.rcms.base.dao.IUserDao;
import com.stone.it.rcms.base.service.IUserService;
import com.stone.it.rcms.base.vo.UserVO;
import com.stone.it.rcms.com.vo.PageResult;
import com.stone.it.rcms.com.vo.PageVO;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * 用户管理配置
 *
 * @author cj.stone
 * @Date 2023/4/26
 * @Desc
 */
@Named
public class UserService implements IUserService {

  @Inject
  private IUserDao userDao;

  @Override
  public PageResult<UserVO> findUserPageResult(UserVO userVO, PageVO pageVO) {
    return userDao.findPageResult(userVO,pageVO);
  }

  @Override
  public UserVO findUserById(String userId) {
    return userDao.findUserId(userId);
  }

  @Override
  public int createUser(UserVO userVO) {
    userVO.setId(UUIDUtil.getUuid());
    return userDao.createUser(userVO);
  }

  @Override
  public int updateUser(String userId, UserVO userVO) {
    userVO.setId(userId);
    return userDao.updateUser(userVO);
  }

  @Override
  public int deleteUser(String userId) {
    return userDao.deleteUser(userId);
  }
}
