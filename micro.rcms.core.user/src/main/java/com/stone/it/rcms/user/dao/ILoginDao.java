package com.stone.it.rcms.user.dao;

import com.stone.it.rcms.user.vo.AuthAccountVO;
import com.stone.it.rcms.user.vo.EnterpriseDetailVO;
import java.util.ArrayList;
import java.util.List;
import org.apache.ibatis.annotations.Param;

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
