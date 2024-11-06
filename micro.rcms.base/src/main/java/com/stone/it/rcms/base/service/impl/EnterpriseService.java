package com.stone.it.rcms.base.service.impl;

import com.stone.it.rcms.base.dao.IEnterpriseDao;
import com.stone.it.rcms.base.service.IEnterpriseService;
import com.stone.it.rcms.base.vo.EnterpriseVO;
import com.stone.it.rcms.core.exception.RcmsApplicationException;
import com.stone.it.rcms.core.util.UUIDUtil;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author cj.stone
 * @Date 2024/11/1
 * @Desc
 */

@Named
public class EnterpriseService implements IEnterpriseService {

    @Inject
    private IEnterpriseDao enterpriseDao;

    @Override
    public List<EnterpriseVO> findEnterpriseList(EnterpriseVO enterpriseVO) {
        return enterpriseDao.findEnterpriseList(enterpriseVO);
    }

    @Override
    public EnterpriseVO findEnterpriseMerchantById(long enterprise_id) {
        return enterpriseDao.findEnterpriseMerchantById(enterprise_id);
    }

    @Override
    public int createEnterpriseMerchant(EnterpriseVO enterpriseVO) {
        // 生成唯一标识码
        enterpriseVO.setCode(UUIDUtil.getUuid());
        // 查看父节点下有没有子节点（包含本节点）
        List<EnterpriseVO> list = enterpriseDao.findEnterpriseById(enterpriseVO.getParentId());
        // 设置节点ID
        enterpriseVO.setId(enterpriseVO.getParentId() * 10 + list.size());
        return enterpriseDao.createEnterpriseMerchant(enterpriseVO);
    }

    @Override
    public int updateEnterpriseMerchant(long enterprise_id, EnterpriseVO enterpriseVO) {
        enterpriseVO.setId(enterprise_id);
        return enterpriseDao.updateEnterpriseMerchant(enterpriseVO);
    }

    @Override
    public int deleteEnterpriseMerchant(long enterprise_id) throws RcmsApplicationException {
        return enterpriseDao.deleteEnterpriseMerchant(enterprise_id);
    }
}
