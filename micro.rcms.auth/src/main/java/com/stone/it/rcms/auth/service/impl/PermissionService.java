package com.stone.it.rcms.auth.service.impl;

import com.stone.it.rcms.auth.dao.IPermissionDao;
import com.stone.it.rcms.auth.service.IPermissionService;
import com.stone.it.rcms.auth.vo.PermissionVO;
import com.stone.it.rcms.core.vo.PageResult;
import com.stone.it.rcms.core.vo.PageVO;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author cj.stone
 * @Date 2024/10/23
 * @Desc
 */
@Named
public class PermissionService implements IPermissionService {

    @Inject
    private IPermissionDao permissionDao;

    @Override
    public PageResult<PermissionVO> findI18nPageResult(PermissionVO permissionVO, PageVO pageVO) {
        return null;
    }

    @Override
    public int updatePermission(String permission_id, PermissionVO permissionVO) {
        return 0;
    }

    @Override
    public PermissionVO findPermissionById(String permission_id) {
        return null;
    }

    @Override
    public List<PermissionVO> findPermissionByPaths(Set<String> apiPaths) {
        List<String> list = new ArrayList<>();
        list.addAll(apiPaths);
        return permissionDao.findPermissionByPaths(list);
    }

    @Override
    public void createPermission(List<PermissionVO> permissionList) {
        permissionDao.createPermission(permissionList);
    }

    @Override
    public void deletePermissionNotInList(Set<String> permissionPathSet) {
        List<String> list = new ArrayList<>();
        list.addAll(permissionPathSet);
        permissionDao.deletePermissionNotInList(list);
    }

    @Override
    public void deletePermissionRelation() {
        permissionDao.deletePermissionRelation();
    }
}
