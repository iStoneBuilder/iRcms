package com.stone.it.rcms.auth.dao;

import com.stone.it.rcms.auth.vo.PermissionVO;
import java.util.List;
import java.util.Set;
import org.apache.ibatis.annotations.Param;

/**
 *
 * @author cj.stone
 * @Date 2024/10/23
 * @Desc
 */
public interface IPermissionDao {

    List<PermissionVO> findPermissionByPaths(@Param("list") List<String> list);

    void createPermission(@Param("list") List<PermissionVO> list);

    void deletePermissionNotInList(@Param("list") List<String> permissionPathSet);

    void deletePermissionRelation();
}
