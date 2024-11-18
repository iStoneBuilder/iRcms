package com.stone.it.rcms.base.dao;

import com.stone.it.rcms.core.vo.PageResult;
import com.stone.it.rcms.core.vo.PageVO;
import com.stone.it.rcms.core.vo.PermissionVO;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * @author cj.stone
 * @Date 2024/11/14
 * @Desc
 */
public interface IPermissionDao {

    PageResult<PermissionVO> findPermissionPageResult(PermissionVO permissionVO, PageVO pageVO);

    List<PermissionVO> findPermissionListByPaths(@Param("apis") List<String> apis);

    int createPermission(@Param("list") List<PermissionVO> dbNotExistApiList);

    int deletePermissionNotInList(@Param("list") List<String> apis);

    int deleteApisRelationAuth();

    void createSuperAdminAuth(@Param("list") List<String> list, @Param("roleCode") String roleCode);
}
