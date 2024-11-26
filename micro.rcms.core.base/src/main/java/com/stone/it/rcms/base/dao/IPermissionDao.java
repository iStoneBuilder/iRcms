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

    int createPermission(@Param("list") List<PermissionVO> dbNotExistApiList);

    int deleteApisRelationAuth(@Param("serviceCode") String serviceCode);

    void createSuperAdminAuth(@Param("list") List<PermissionVO> list, @Param("roleCode") String roleCode,
        @Param("createBy") String createBy);

    List<PermissionVO> findCurrentServiceApiByServiceCode(@Param("serviceCode") String serviceCode);

    int deletePermissionNotExist(@Param("list") List<PermissionVO> list, @Param("serviceCode") String serviceCode);
}
