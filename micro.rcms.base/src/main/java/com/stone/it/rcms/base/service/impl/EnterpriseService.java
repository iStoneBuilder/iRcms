package com.stone.it.rcms.base.service.impl;

import com.stone.it.rcms.base.service.IEnterpriseService;
import com.stone.it.rcms.base.vo.EnterpriseVO;
import com.stone.it.rcms.core.vo.PageResult;
import com.stone.it.rcms.core.vo.PageVO;
import javax.inject.Named;

/**
 *
 * @author cj.stone
 * @Date 2024/11/1
 * @Desc
 */

@Named
public class EnterpriseService implements IEnterpriseService {

    @Override
    public PageResult<EnterpriseVO> findI18nPageResult(EnterpriseVO enterpriseVO, PageVO pageVO) {
        return null;
    }

    @Override
    public EnterpriseVO findEnterpriseMerchantById(String enterprise_id) {
        return null;
    }

    @Override
    public int createEnterpriseMerchant(EnterpriseVO enterpriseVO) {
        return 0;
    }

    @Override
    public int updateEnterpriseMerchant(String enterprise_id, EnterpriseVO enterpriseVO) {
        return 0;
    }

    @Override
    public int deleteEnterpriseMerchant(String enterprise_id) {
        return 0;
    }
}
