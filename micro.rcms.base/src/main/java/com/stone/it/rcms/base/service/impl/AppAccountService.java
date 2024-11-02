package com.stone.it.rcms.base.service.impl;

import com.alibaba.fastjson2.JSONObject;
import com.stone.it.rcms.base.service.IAppAccountService;
import com.stone.it.rcms.base.vo.AppAccountVO;
import javax.inject.Named;

/**
 *
 * @author cj.stone
 * @Date 2024/11/2
 * @Desc
 */
@Named
public class AppAccountService implements IAppAccountService {

    @Override
    public JSONObject createAppAccount(AppAccountVO appAccountVO) {
        return null;
    }

    @Override
    public JSONObject deleteAppAccount(String accountCode) {
        return null;
    }

    @Override
    public JSONObject updateAppAccount(String accountCode, AppAccountVO appAccountVO) {
        return null;
    }
}
