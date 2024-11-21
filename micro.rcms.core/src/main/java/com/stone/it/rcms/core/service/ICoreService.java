package com.stone.it.rcms.core.service;

import com.stone.it.rcms.core.vo.RcmsLogVO;
import org.springframework.stereotype.Service;

/**
 *
 * @author cj.stone
 * @Date 2024/11/21
 * @Desc
 */
@Service
public abstract class ICoreService {

    public abstract void createRcmsOpLog(RcmsLogVO rcmsLogVO);

}
