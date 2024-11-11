package com.stone.it.rcms.base.service.impl;

import com.stone.it.rcms.base.dao.IAccountDao;
import com.stone.it.rcms.base.service.IAccountService;
import com.stone.it.rcms.base.service.IEnterpriseService;
import com.stone.it.rcms.base.vo.AccountVO;
import com.stone.it.rcms.base.vo.EnterpriseVO;
import com.stone.it.rcms.core.vo.PageResult;
import com.stone.it.rcms.core.vo.PageVO;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;
import org.apache.shiro.SecurityUtils;

/**
 * @author cj.stone
 * @Date 2024/11/2
 * @Desc
 */
@Named
public class AccountService implements IAccountService {

  @Inject
  private IEnterpriseService enterpriseService;

  @Inject
  private IAccountDao accountDao;

  @Override
  public PageResult<AccountVO> findAccountPageResult(AccountVO accountVO, PageVO pageVO) {
    List<EnterpriseVO> list = enterpriseService.findEnterpriseList(
      accountVO.getEnterpriseId(),
      SecurityUtils.getSubject());
    return accountDao.findAccountPageResult(accountVO, pageVO, list);
  }

  @Override
  public List<AccountVO> findAccountList(AccountVO accountVO) {
    return accountDao.findAccountList(accountVO,
      enterpriseService.findEnterpriseList(accountVO.getEnterpriseId(),
        SecurityUtils.getSubject()));
  }

  @Override
  public int createAccount(AccountVO accountVO) {
    return accountDao.createAccount(accountVO);
  }

  @Override
  public int deleteAccount(String code) {
    return accountDao.deleteAccount(code);
  }

  @Override
  public int updateAccount(String code, AccountVO accountVO) {
    accountVO.setCode(code);
    return accountDao.updateAccount(accountVO);
  }
}
