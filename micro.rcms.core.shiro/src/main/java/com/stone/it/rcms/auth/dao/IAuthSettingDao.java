package com.stone.it.rcms.auth.dao;

import com.stone.it.rcms.auth.vo.AuthAccountVO;
import com.stone.it.rcms.auth.vo.SystemApiVO;
import com.stone.it.rcms.auth.vo.EnterpriseDetailVO;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * @author cj.stone
 * @Date 2024/11/1
 * @Desc
 */
public interface IAuthSettingDao {

    AuthAccountVO getUserInfoByUserId(@Param("accountCode") String accountCode);

    List<SystemApiVO> getApiPathByRoleCodes(@Param("list") List<String> list);

    EnterpriseDetailVO findAccountEnterpriseById(@Param("enterpriseId") String enterpriseId);

    List<String> findPermissionsByRoleList(@Param("list") List<String> roleList);
}
