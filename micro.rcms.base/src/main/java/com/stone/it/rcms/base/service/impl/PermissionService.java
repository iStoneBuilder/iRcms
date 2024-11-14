package com.stone.it.rcms.base.service.impl;

import com.stone.it.rcms.base.dao.IPermissionDao;
import com.stone.it.rcms.base.service.IPermissionService;
import com.stone.it.rcms.base.vo.PermissionVO;
import com.stone.it.rcms.core.vo.PageResult;
import com.stone.it.rcms.core.vo.PageVO;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * @author cj.stone
 * @Date 2024/11/14
 * @Desc
 */
@Named
public class PermissionService implements IPermissionService {

  @Inject
  private IPermissionDao permissionDao;

  @Override
  public PageResult<PermissionVO> findPermissionPageResult(PermissionVO permissionVO,
    PageVO pageVO) {
    return permissionDao.findPermissionPageResult(permissionVO, pageVO);
  }
}
