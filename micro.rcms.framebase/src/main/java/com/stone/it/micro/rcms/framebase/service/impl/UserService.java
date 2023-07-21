package com.stone.it.micro.rcms.framebase.service.impl;

import com.stone.it.micro.rcms.common.utils.UUIDUtil;
import com.stone.it.micro.rcms.framebase.dao.IUserDao;
import com.stone.it.micro.rcms.framebase.service.IUserService;
import com.stone.it.micro.rcms.framebase.vo.UserVO;
import com.stone.it.micro.rcms.framecom.vo.PageResult;
import com.stone.it.micro.rcms.framecom.vo.PageVO;
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
