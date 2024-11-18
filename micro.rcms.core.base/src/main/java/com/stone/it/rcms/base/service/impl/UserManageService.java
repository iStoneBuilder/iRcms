package com.stone.it.rcms.base.service.impl;

import com.stone.it.rcms.base.dao.IUserManageDao;
import com.stone.it.rcms.base.service.IUserManageService;
import com.stone.it.rcms.base.vo.UserVO;
import com.stone.it.rcms.core.vo.PageResult;
import com.stone.it.rcms.core.vo.PageVO;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * 用户管理配置
 *
 * @author cj.stone
 * @Desc
 */
@Named
public class UserManageService implements IUserManageService {

    @Inject
    private IUserManageDao userDao;

    @Override
    public PageResult<UserVO> findUserPageResult(UserVO userVO, PageVO pageVO) {
        return userDao.findPageResult(userVO, pageVO);
    }

    @Override
    public UserVO findUserById(String userId) {
        return userDao.findUserId(userId);
    }

}
