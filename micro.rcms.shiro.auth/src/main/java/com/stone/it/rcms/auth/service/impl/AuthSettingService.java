package com.stone.it.rcms.auth.service.impl;

import com.stone.it.rcms.auth.dao.IAuthSettingDao;
import com.stone.it.rcms.auth.service.IAuthSettingService;
import com.stone.it.rcms.auth.vo.AuthAccountVO;
import com.stone.it.rcms.auth.vo.SystemApiVO;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author cj.stone
 * @Date 2024/11/1
 * @Desc
 */
@Named
public class AuthSettingService implements IAuthSettingService {

    @Inject
    private IAuthSettingDao authSettingDao;

    @Override
    public List<SystemApiVO> findApiPathsByPaths(Set<String> apiPaths) {
        List<String> list = new ArrayList<>(apiPaths);
        return authSettingDao.findApiPathsByPaths(list);
    }

    @Override
    public void createApiPaths(List<SystemApiVO> permissionList) {
        authSettingDao.createApiPaths(permissionList);
    }

    @Override
    public void deleteApiPathsNotInList(Set<String> permissionPathSet) {
        List<String> list = new ArrayList<>(permissionPathSet);
        authSettingDao.deleteApiPathsNotInList(list);
    }

    @Override
    public void deleteApisRelationAuth() {
        authSettingDao.deleteApisRelationAuth();
    }

    @Override
    public AuthAccountVO getUserInfoByUserId(String accountCode) {
        return authSettingDao.getUserInfoByUserId(accountCode);
    }

    @Override
    public List<SystemApiVO> getApiPathByRoleCodes(Set<String> roleSets) {
        List<String> list = new ArrayList<>(roleSets);
        return authSettingDao.getApiPathByRoleCodes(list);
    }

    @Override
    public void createSuperAdminAuth(Set<String> authCodeSet, String roleCode) {
        List<String> list = new ArrayList<>(authCodeSet);
        authSettingDao.createSuperAdminAuth(list, roleCode);
    }
}
