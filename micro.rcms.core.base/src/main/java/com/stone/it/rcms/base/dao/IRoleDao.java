package com.stone.it.rcms.base.dao;

import com.stone.it.rcms.base.vo.RoleVO;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * @author cj.stone
 * @Desc
 */
public interface IRoleDao {

  int createRole(RoleVO roleVO);

  int updateRole(RoleVO roleVO);

  int deleteRole(@Param("roleId") String roleId);

  List<RoleVO> findRoleByEnterpriseId(@Param("enterpriseId") String enterpriseId);

  List<RoleVO> findAllRoles();
}
