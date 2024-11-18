package com.stone.it.rcms.base.dao;

import com.stone.it.rcms.base.vo.AccountVO;
import com.stone.it.rcms.base.vo.EnterpriseVO;
import com.stone.it.rcms.core.vo.PageResult;
import com.stone.it.rcms.core.vo.PageVO;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * @author cj.stone
 * @Date 2024/11/11
 * @Desc
 */
public interface IAccountDao {

  List<AccountVO> findAccountList(AccountVO accountVO, List<EnterpriseVO> list);

  int updateAccount(AccountVO accountVO);

  int deleteAccount(@Param("code") String code);

  int createAccount(AccountVO accountVO);

  PageResult<AccountVO> findAccountPageResult(@Param("param1") AccountVO accountVO,
    @Param("param2") PageVO pageVO,
    @Param("param3") List<EnterpriseVO> enterpriseList);
}
