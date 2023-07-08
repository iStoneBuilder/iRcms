package com.stone.it.micro.rcms.framebase.service.impl;

import com.stone.it.micro.rcms.framebase.dao.IRoleDao;
import com.stone.it.micro.rcms.framebase.service.IRoleService;
import com.stone.it.micro.rcms.framebase.vo.RoleVO;
import com.stone.it.micro.rcms.framecore.util.UUIDUtil;
import com.stone.it.micro.rcms.framecore.vo.PageVO;
import com.stone.it.micro.rcms.framecore.vo.PagedResult;
import com.stone.it.micro.rcms.framecore.vo.ResultVO;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * 角色配置
 *
 * @author cj.stone
 * @Date 2023/4/26
 * @Desc
 */
@Named
public class RoleService implements IRoleService {

  @Inject
  private IRoleDao roleDao;

  @Override
  public PagedResult<RoleVO> findRolePageResult(RoleVO roleVO, PageVO pageVO) {
    return roleDao.findPageResult(roleVO,pageVO);
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
