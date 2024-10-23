package com.stone.it.rcms.auth.service.impl;

import com.stone.it.rcms.auth.service.IPermissionService;
import com.stone.it.rcms.auth.vo.PermissionVO;
import com.stone.it.rcms.core.vo.PageResult;
import com.stone.it.rcms.core.vo.PageVO;
import java.util.List;
import java.util.Set;
import javax.inject.Named;

/**
 *
 * @author cj.stone
 * @Date 2024/10/23
 * @Desc
 */
@Named
public class PermissionService implements IPermissionService {

    @Override
    public PageResult<PermissionVO> findI18nPageResult(PermissionVO permissionVO, PageVO pageVO) {
        return null;
    }

    @Override
    public int updatePermission(String permission_id, PermissionVO permissionVO) {
        return 0;
    }

    @Override
    public PermissionVO getPermission(String permission_id) {
        return null;
    }

    @Override
    public List<PermissionVO> getPermissionByPaths(Set<String> apiPaths) {
        return List.of();
    }

    @Override
    public void createPermission(List<PermissionVO> permissionList) {

    }

}
