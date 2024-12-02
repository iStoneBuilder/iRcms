package com.stone.it.rcms.mifi.common.service.impl;

import com.alibaba.fastjson2.JSONObject;
import com.stone.it.rcms.core.util.TreeUtil;
import com.stone.it.rcms.mifi.common.dao.ICommonDao;
import com.stone.it.rcms.mifi.common.service.ICommonService;
import com.stone.it.rcms.mifi.common.vo.CommonVO;

import javax.inject.Inject;
import java.util.List;

/**
 *
 * @author cj.stone
 * @Date 2024/12/2
 * @Desc
 */
public class CommonService implements ICommonService {

    @Inject
    private ICommonDao commonDao;

    @Override
    public List<CommonVO> findEnterpriseListByParentId(String parentId) {
        CommonVO root = commonDao.findEnterpriseMerchantById(parentId);
        List<CommonVO> list = commonDao.findEnterpriseListByParentId(parentId);
        JSONObject tree = TreeUtil.buildTree(root, list);
        CommonVO treeNode = JSONObject.parseObject(tree.toJSONString(), CommonVO.class);
        JSONObject listNode = TreeUtil.treeToList(treeNode);
        CommonVO nodeVO = JSONObject.parseObject(JSONObject.toJSONString(listNode), CommonVO.class);
        return nodeVO.getChildren();
    }

    @Override
    public List<CommonVO> findEnterpriseListByTenantId(String tenantId) {
        return commonDao.findEnterpriseListByTenantId(tenantId);
    }
}
