package com.stone.it.micro.rcms.framebase.dao;

import com.stone.it.micro.rcms.framebase.vo.ClassifyVO;
import com.stone.it.micro.rcms.framecore.vo.PageVO;
import com.stone.it.micro.rcms.framecore.vo.PagedResult;

/**
 * @author cj.stone
 * @Date 2023/3/9
 * @Desc
 */
public interface IItemDao {

  PagedResult<ClassifyVO> findPageResult(ClassifyVO classifyVO, PageVO pageVO);
}
