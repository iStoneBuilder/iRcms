package com.stone.it.rcms.base.dao;

import com.stone.it.rcms.base.vo.UserVO;
import com.stone.it.rcms.core.vo.PageResult;
import com.stone.it.rcms.core.vo.PageVO;

/**
 * @author cj.stone
 * @Desc
 */
public interface IUserManageDao {

    PageResult<UserVO> findPageResult(UserVO UserVO, PageVO pageVO);

    UserVO findUserId(String userId);

}
