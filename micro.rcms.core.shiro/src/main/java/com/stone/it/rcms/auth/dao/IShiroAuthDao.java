package com.stone.it.rcms.auth.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * @author cj.stone
 * @Date 2024/11/1
 * @Desc
 */
public interface IShiroAuthDao {

    String findAccountRoleById(@Param("userId") String userId);

    List<String> findPermsByRoleCodes(@Param("list") List<String> list);
}
