package com.stone.it.micro.rcms.framebase.dao;

import com.stone.it.micro.rcms.framebase.vo.RoleVO;
import com.stone.it.micro.rcms.framecore.vo.PageVO;
import com.stone.it.micro.rcms.framecore.vo.PageResult;

/**
 * @author cj.stone
 * @Date 2023/4/26
 * @Desc
 */
public interface IRoleDao {

  PageResult<RoleVO> findPageResult(RoleVO roleVO, PageVO pageVO);

  RoleVO findRoleId(String roleId);

  int createRole(RoleVO roleVO);

  int updateRole(RoleVO roleVO);

  int deleteRole(String roleId);
}
