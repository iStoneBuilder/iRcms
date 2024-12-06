package com.stone.it.rcms.base.dao;

import com.stone.it.rcms.base.vo.RoleVO;
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

    int createPermission(@Param("list") List<PermissionVO> dbNotExistApiList, @Param("createBy") String createBy);

    int deleteApisRelationAuth(@Param("serviceCode") String serviceCode);

    int createRolePermission(@Param("list") List<PermissionVO> list, @Param("roleCode") String roleCode,
        @Param("createBy") String createBy);

    List<PermissionVO> findCurrentServiceApiByServiceCode(@Param("serviceCode") String serviceCode);

    int deletePermissionNotExist(@Param("list") List<PermissionVO> list, @Param("serviceCode") String serviceCode);

    int deleteRolePermission(@Param("roleCode") String roleCode);

    int deleteRolePermissionNotExist(@Param("pList") List<PermissionVO> permissionList,
        @Param("rList") List<RoleVO> roleList);

    List<PermissionVO> findRolePermissionList(RoleVO roleVO);
}
