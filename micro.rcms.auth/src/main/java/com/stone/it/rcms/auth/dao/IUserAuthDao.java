package com.stone.it.rcms.auth.dao;

import com.stone.it.rcms.auth.vo.AuthRoleVO;
import com.stone.it.rcms.auth.vo.AuthUserVO;
import com.stone.it.rcms.auth.vo.PermissionVO;
import java.util.Collection;
import java.util.List;
import org.apache.ibatis.annotations.Param;

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
    AuthUserVO getUserInfoByUserId(@Param("userId") String userId);

    /**
     * 根据用户ID获取用户角色信息
     *
     * @param userId
     * @return
     */
    List<AuthRoleVO> getUserRoleInfoByUserId(@Param("userId") String userId);

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
     * @param appId
     * @return
     */
    List<PermissionVO> getPermissionByAccountId(@Param("appId") String appId);
}
