package com.stone.it.rcms.base.service.impl;

import com.alibaba.fastjson2.JSONObject;
import com.stone.it.rcms.base.dao.IEnterpriseDao;
import com.stone.it.rcms.base.service.IEnterpriseService;
import com.stone.it.rcms.base.vo.EnterpriseVO;
import com.stone.it.rcms.core.exception.RcmsApplicationException;
import com.stone.it.rcms.core.util.TreeUtil;
import com.stone.it.rcms.core.util.UUIDUtil;
import com.stone.it.rcms.core.util.UserUtil;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;
import org.apache.cxf.common.util.StringUtils;
import org.apache.shiro.subject.Subject;

/**
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
        EnterpriseVO tree = this.findEnterpriseTree(enterpriseVO);
        JSONObject node = TreeUtil.treeToList(tree);
        EnterpriseVO nodeVO = JSONObject.parseObject(JSONObject.toJSONString(node), EnterpriseVO.class);
        return nodeVO.getChildren();
    }

    @Override
    public List<EnterpriseVO> findEnterpriseListByPid(EnterpriseVO enterpriseVO) {
        return enterpriseDao.findEnterpriseListByPid(enterpriseVO);
    }

    @Override
    public EnterpriseVO findEnterpriseTree(EnterpriseVO enterpriseVO) {
        // 查询根节点数据
        EnterpriseVO root = enterpriseDao.findEnterpriseMerchantById(enterpriseVO.getId());
        // 查询所有子节点数据
        List<EnterpriseVO> list = enterpriseDao.findEnterpriseList(new EnterpriseVO());
        // 构建树形结构数据
        JSONObject tree = TreeUtil.buildTree(root, list);
        return JSONObject.parseObject(tree.toJSONString(), EnterpriseVO.class);
    }

    @Override
    public EnterpriseVO findEnterpriseMerchantById(String enterpriseId) {
        return enterpriseDao.findEnterpriseMerchantById(enterpriseId);
    }

    @Override
    public int createEnterpriseMerchant(EnterpriseVO enterpriseVO) {
        // 设置企业ID
        enterpriseVO.setId(UUIDUtil.getUuid());
        // 判断当前创建的企业，tenantID=enterpriseId
        if ("enterprise".equals(enterpriseVO.getType())) {
            enterpriseVO.setTenantId(enterpriseVO.getId());
        } else if ("merchant".equals(enterpriseVO.getType())) {
            enterpriseVO.setTenantId(findTenantIdByEnterpriseId(enterpriseVO.getParentId()));
        }
        return enterpriseDao.createEnterpriseMerchant(enterpriseVO);
    }

    private String findTenantIdByEnterpriseId(String parentId) {
        EnterpriseVO vo = enterpriseDao.findEnterpriseMerchantById(parentId);
        if ("enterprise".equals(vo.getType())) {
            return vo.getId();
        }
        return findTenantIdByEnterpriseId(vo.getParentId());
    }

    @Override
    public int updateEnterpriseMerchant(String enterpriseId, EnterpriseVO enterpriseVO) {
        enterpriseVO.setId(enterpriseId);
        return enterpriseDao.updateEnterpriseMerchant(enterpriseVO);
    }

    @Override
    public int deleteEnterpriseMerchant(String enterpriseId) throws RcmsApplicationException {
        return enterpriseDao.deleteEnterpriseMerchant(enterpriseId);
    }

    @Override
    public List<EnterpriseVO> findEnterpriseList(String enterpriseId, Subject subject) {
        EnterpriseVO enterpriseVO = new EnterpriseVO();
        List<EnterpriseVO> list;
        // 如果没有查询企业ID，则获取当前登录用户的企业ID
        if (StringUtils.isEmpty(enterpriseId)) {
            enterpriseVO.setId(UserUtil.getEnterpriseId());
            list = this.findEnterpriseList(enterpriseVO);
        } else {
            list = new ArrayList<>();
            enterpriseVO.setId(enterpriseId);
            list.add(enterpriseVO);
        }
        return list;
    }

}
