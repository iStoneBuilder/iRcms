package com.stone.it.rcms.base.dao;

import com.stone.it.rcms.base.vo.PermissionVO;
import com.stone.it.rcms.core.vo.PageResult;
import com.stone.it.rcms.core.vo.PageVO;

/**
 * @author cj.stone
 * @Date 2024/11/14
 * @Desc
 */
public interface IPermissionDao {

  PageResult<PermissionVO> findPermissionPageResult(PermissionVO permissionVO, PageVO pageVO);
}
