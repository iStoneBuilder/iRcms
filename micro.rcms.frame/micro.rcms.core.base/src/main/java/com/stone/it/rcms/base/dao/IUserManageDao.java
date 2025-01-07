package com.stone.it.rcms.base.dao;

import com.stone.it.rcms.base.vo.UserManageVO;
import com.stone.it.rcms.core.vo.PageResult;
import com.stone.it.rcms.core.vo.PageVO;

/**
 * @author cj.stone
 * @Desc
 */
public interface IUserManageDao {

    PageResult<UserManageVO> findPageResult(UserManageVO UserVO, PageVO pageVO);

    UserManageVO findUserId(String userId);

}
