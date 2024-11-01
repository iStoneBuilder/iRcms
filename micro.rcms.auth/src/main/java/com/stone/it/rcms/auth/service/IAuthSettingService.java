package com.stone.it.rcms.auth.service;

import com.stone.it.rcms.auth.vo.AccountVO;
import com.stone.it.rcms.auth.vo.AuthApisVO;
import com.stone.it.rcms.auth.vo.AuthUserVO;
import java.util.List;
import java.util.Set;
import org.checkerframework.checker.units.qual.A;

/**
 *
 * @author cj.stone
 * @Date 2024/11/1
 * @Desc
 */
public interface IAuthSettingService {

    /**
     * 查询已存在的服务信息
     *
     * @param apiPaths
     * @return
     */
    List<AuthApisVO> findApiPathsByPaths(Set<String> apiPaths);

    /**
     * 创建新增的权限路径
     *
     * @param permissionList
     */
    void createApiPaths(List<AuthApisVO> permissionList);

    /**
     * 删除不存在的权限信息
     *
     * @param permissionPathSet
     */
    void deleteApiPathsNotInList(Set<String> permissionPathSet);

    /**
     * 清理不存在授权关系
     *
     */
    void deleteApisRelationAuth();

    AccountVO getUserInfoByUserId(String accountCode);

    List<AuthApisVO> getApiPathByRoleCodes(Set<String> roleSets);
}
