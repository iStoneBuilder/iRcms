package com.stone.it.rcms.mifi.sim.dao;

import com.stone.it.rcms.core.vo.PageResult;
import com.stone.it.rcms.core.vo.PageVO;
import com.stone.it.rcms.mifi.sim.vo.MerchantVO;

/**
 *
 * @author cj.stone
 * @Date 2024/11/19
 * @Desc
 */
public interface IMerchantDao {

    PageResult<MerchantVO> findMerchantPageResult(MerchantVO merchantVO, PageVO pageVO);
}
