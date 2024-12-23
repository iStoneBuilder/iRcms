package com.stone.it.rcms.mifi.device.dao;

import com.stone.it.rcms.core.vo.PageResult;
import com.stone.it.rcms.core.vo.PageVO;
import com.stone.it.rcms.mifi.sim.vo.SimNameVO;

/**
 *
 * @author cj.stone
 * @Date 2024/12/23
 * @Desc
 */
public interface IDeviceRealNameDao {
    PageResult<SimNameVO> findSimNamePageResult(SimNameVO simNameVO, PageVO pageVO);

    int createSimName(SimNameVO simNameVO);

    int syncSimName(SimNameVO simNameVO);

    int cleanSimName(SimNameVO simNameVO);
}
