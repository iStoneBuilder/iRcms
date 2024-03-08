package com.stone.it.rcms.base.dao;

import com.stone.it.rcms.base.vo.RoleVO;
import com.stone.it.rcms.core.vo.PageResult;
import com.stone.it.rcms.core.vo.PageVO;

/**
 * @author cj.stone
 * @Desc
 */
public interface IRoleDao {

  PageResult<RoleVO> findPageResult(RoleVO roleVO, PageVO pageVO);

  RoleVO findRoleId(String roleId);

  int createRole(RoleVO roleVO);

  int updateRole(RoleVO roleVO);

  int deleteRole(String roleId);
}
