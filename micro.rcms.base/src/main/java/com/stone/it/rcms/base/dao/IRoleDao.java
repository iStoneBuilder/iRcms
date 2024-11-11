package com.stone.it.rcms.base.dao;

import com.stone.it.rcms.base.vo.RoleVO;
import com.stone.it.rcms.core.vo.PageResult;
import com.stone.it.rcms.core.vo.PageVO;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * @author cj.stone
 * @Desc
 */
public interface IRoleDao {

  int createRole(RoleVO roleVO);

  int updateRole(RoleVO roleVO);

  int deleteRole(String roleId);

  List<RoleVO> findRoleByEnterpriseId(@Param("enterpriseId") String enterpriseId);

  List<RoleVO> findAllRoles();

}
