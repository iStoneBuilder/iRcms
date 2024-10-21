package com.stone.it.rcms.auth.dao;

import com.stone.it.rcms.auth.vo.AuthRoleVO;
import com.stone.it.rcms.auth.vo.AuthUserVO;
import com.stone.it.rcms.auth.vo.PermissionVO;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author cj.stone
 * @Date 2024/10/21
 * @Desc
 */
public interface IUserAuthDao {

    /**
     * 根据用户ID获取用户信息
     *
     * @param userId
     * @return
     */
    AuthUserVO getUserInfoByUserId(String userId);

    /**
     * 根据用户ID获取用户角色信息
     *
     * @param userId
     * @return
     */
    List<AuthRoleVO> getUserRoleInfoByUserId(String userId);

    /**
     * 根据角色ID获取角色权限信息
     *
     * @param roleSets
     * @return
     */
    List<PermissionVO> getPermissionByRoleCodes(Collection<String> roleSets);

    /**
     * 根据账号ID获取账号权限信息
     *
     * @param accountId
     * @return
     */
    List<PermissionVO> getPermissionByAccountId(String accountId);
}
