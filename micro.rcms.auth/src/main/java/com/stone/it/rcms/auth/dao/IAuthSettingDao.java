package com.stone.it.rcms.auth.dao;

import com.stone.it.rcms.auth.vo.AccountVO;
import com.stone.it.rcms.auth.vo.AuthApisVO;
import com.stone.it.rcms.auth.vo.AuthUserVO;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 *
 * @author cj.stone
 * @Date 2024/11/1
 * @Desc
 */
public interface IAuthSettingDao {

    List<AuthApisVO> findApiPathsByPaths(@Param("list") List<String> list);

    void createApiPaths(@Param("list") List<AuthApisVO> list);

    void deleteApiPathsNotInList(@Param("list") List<String> permissionPathSet);

    void deleteApisRelationAuth();

    AccountVO getUserInfoByUserId(@Param("userCode") String accountCode);

    List<AuthApisVO> getApiPathByRoleCodes(@Param("list") List<String> list);
}
