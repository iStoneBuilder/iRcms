package com.stone.it.rcms.auth.service.impl;

import com.stone.it.rcms.auth.dao.IUserAuthDao;
import com.stone.it.rcms.auth.service.IUserAuthService;
import com.stone.it.rcms.auth.vo.AuthRoleVO;
import com.stone.it.rcms.auth.vo.AuthUserVO;
import com.stone.it.rcms.auth.vo.PermissionVO;
import java.util.List;
import java.util.Set;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author cj.stone
 * @Date 2024/10/21
 * @Desc
 */
@Named
public class UserAuthService implements IUserAuthService {

    @Inject
    private IUserAuthDao userAuthDao;

    @Override
    public AuthUserVO getUserInfoByUserId(String userId) {
        return userAuthDao.getUserInfoByUserId(userId);
    }

    @Override
    public List<AuthRoleVO> getUserRoleInfoByUserId(String userId) {
        return userAuthDao.getUserRoleInfoByUserId(userId);
    }

    @Override
    public List<PermissionVO> getPermissionByRoleCodes(Set<String> roleSets) {
        return userAuthDao.getPermissionByRoleCodes(roleSets);
    }
}
