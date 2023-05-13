package com.stone.it.micro.rcms.framebase.service.impl;

import com.stone.it.micro.rcms.framebase.dao.IUserDao;
import com.stone.it.micro.rcms.framebase.service.IUserService;
import com.stone.it.micro.rcms.framebase.vo.UserVO;
import com.stone.it.micro.rcms.framecore.util.UUIDUtil;
import com.stone.it.micro.rcms.framecore.vo.PageVO;
import com.stone.it.micro.rcms.framecore.vo.PagedResult;
import com.stone.it.micro.rcms.framecore.vo.ResultVO;
import javax.inject.Inject;

/**
 * 用户管理配置
 *
 * @author cj.stone
 * @Date 2023/4/26
 * @Desc
 */
public class UserService implements IUserService {

  @Inject
  private IUserDao userDao;

  @Override
  public PagedResult<UserVO> findPageResult(UserVO userVO, PageVO pageVO) {
    return userDao.findPageResult(userVO,pageVO);
  }

  @Override
  public ResultVO findUserById(String userId) {
    return new ResultVO(userDao.findUserId(userId));
  }

  @Override
  public ResultVO createUser(UserVO userVO) {
    userVO.setId(UUIDUtil.getUuid());
    userDao.createUser(userVO);
    return new ResultVO(userVO);
  }

  @Override
  public ResultVO updateUser(String userId, UserVO userVO) {
    userVO.setId(userId);
    userDao.updateUser(userVO);
    return new ResultVO(userVO);
  }

  @Override
  public ResultVO deleteUser(String userId) {
    userDao.deleteUser(userId);
    return new ResultVO();
  }
}
