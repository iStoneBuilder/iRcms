package com.stone.it.rcms.base.service.impl;

import com.stone.it.rcms.base.dao.IUserManageDao;
import com.stone.it.rcms.base.service.IUserManageService;
import com.stone.it.rcms.base.vo.UserManageVO;
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
    public PageResult<UserManageVO> findUserPageResult(UserManageVO userVO, PageVO pageVO) {
        return userDao.findPageResult(userVO, pageVO);
    }

    @Override
    public UserManageVO findUserById(String userId) {
        return userDao.findUserId(userId);
    }

}
