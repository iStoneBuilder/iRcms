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
  public PagedResult<RoleVO> findPageResult(RoleVO roleVO, PageVO pageVO) {
    return roleDao.findPageResult(roleVO,pageVO);
  }

  @Override
  public ResultVO findRoleById(String roleId) {
    return new ResultVO(roleDao.findRoleId(roleId));
  }

  @Override
  public ResultVO createRole(RoleVO roleVO) {
    roleVO.setRoleId(UUIDUtil.getUuid());
    roleDao.createRole(roleVO);
    return new ResultVO(roleVO);
  }

  @Override
  public ResultVO updateRole(String roleId, RoleVO roleVO) {
    roleVO.setRoleId(roleId);
    roleDao.updateRole(roleVO);
    return new ResultVO(roleVO);
  }

  @Override
  public ResultVO deleteRole(String roleId) {
    roleDao.deleteRole(roleId);
    return new ResultVO();
  }
}
