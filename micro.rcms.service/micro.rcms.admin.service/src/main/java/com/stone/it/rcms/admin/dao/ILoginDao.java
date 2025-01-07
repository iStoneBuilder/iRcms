package com.stone.it.rcms.admin.dao;

import com.stone.it.rcms.admin.vo.AuthAccountVO;
import com.stone.it.rcms.admin.vo.EnterpriseDetailVO;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author cj.stone
 * @Date 2024/11/19
 * @Desc
 */
public interface ILoginDao {

    AuthAccountVO findAccountInfoById(@Param("account") String account);

    List<String> findPermsByRoles(ArrayList<String> roleList);

    EnterpriseDetailVO findEnterpriseDetailById(@Param("enterpriseId") String enterpriseId);
}
