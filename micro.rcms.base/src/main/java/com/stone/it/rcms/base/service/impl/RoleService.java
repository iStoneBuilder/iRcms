package com.stone.it.rcms.base.service.impl;

import com.stone.it.rcms.base.dao.IRoleDao;
import com.stone.it.rcms.base.service.IRoleService;
import com.stone.it.rcms.base.vo.RoleVO;
import com.stone.it.rcms.core.util.UUIDUtil;
import com.stone.it.rcms.core.vo.PageResult;
import com.stone.it.rcms.core.vo.PageVO;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * 角色配置
 *
 * @author cj.stone
 * @Desc
 */
@Named
public class RoleService implements IRoleService {

    @Inject
    private IRoleDao roleDao;

    @Override
    public PageResult<RoleVO> findRolePageResult(RoleVO roleVO, PageVO pageVO) {
        return roleDao.findPageResult(roleVO, pageVO);
    }

    @Override
    public List<RoleVO> findRoleList(String parent_code) {
        return List.of();
    }

    @Override
    public RoleVO findRoleById(String roleId) {
        return roleDao.findRoleId(roleId);
    }

    @Override
    public int createRole(RoleVO roleVO) {
        roleVO.setRoleId(UUIDUtil.getUuid());
        return roleDao.createRole(roleVO);
    }

    @Override
    public int updateRole(String roleId, RoleVO roleVO) {
        roleVO.setRoleId(roleId);
        return roleDao.updateRole(roleVO);
    }

    @Override
    public int deleteRole(String roleId) {
        return roleDao.deleteRole(roleId);
    }
}
