package com.stone.it.rcms.base.service.impl;

import com.stone.it.rcms.base.dao.IAccountDao;
import com.stone.it.rcms.base.service.IAccountService;
import com.stone.it.rcms.base.service.IEnterpriseService;
import com.stone.it.rcms.base.vo.AccountVO;
import com.stone.it.rcms.base.vo.EnterpriseVO;
import com.stone.it.rcms.core.util.UserUtil;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;
import org.apache.cxf.common.util.StringUtils;
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
  public List<AccountVO> getAccountList(AccountVO accountVO) {
    EnterpriseVO enterpriseVO = new EnterpriseVO();
    List<EnterpriseVO> list;
    // 如果没有查询企业ID，则获取当前登录用户的企业ID
    if (StringUtils.isEmpty(accountVO.getEnterpriseId())) {
      enterpriseVO.setId(UserUtil.getEnterpriseId(SecurityUtils.getSubject()));
      list = enterpriseService.findEnterpriseList(enterpriseVO);
    } else {
      list = new ArrayList<>();
      enterpriseVO.setId(accountVO.getEnterpriseId());
      list.add(enterpriseVO);
    }
    return accountDao.findAccountList(accountVO, list);
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
