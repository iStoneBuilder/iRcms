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
    EnterpriseVO tree = this.findEnterpriseTreeById(enterpriseVO);
    JSONObject node = TreeUtil.treeToList(tree);
    EnterpriseVO nodeVO = JSONObject.parseObject(JSONObject.toJSONString(node), EnterpriseVO.class);
    return nodeVO.getChildren();
  }

  @Override
  public EnterpriseVO findEnterpriseTreeById(EnterpriseVO enterpriseVO) {
    // 查询根节点数据
    EnterpriseVO root = enterpriseDao.findEnterpriseMerchantById(enterpriseVO.getId());
    // 查询所有子节点数据
    List<EnterpriseVO> list = enterpriseDao.findEnterpriseList(new EnterpriseVO());
    // 构建树形结构数据
    JSONObject tree = TreeUtil.buildTree(root, list);
    return JSONObject.parseObject(tree.toJSONString(), EnterpriseVO.class);
  }

  @Override
  public EnterpriseVO findEnterpriseMerchantById(String enterprise_id) {
    return enterpriseDao.findEnterpriseMerchantById(enterprise_id);
  }

  @Override
  public int createEnterpriseMerchant(EnterpriseVO enterpriseVO) {
    // 生成唯一标识码
    enterpriseVO.setCode(UUIDUtil.getUuid());
    // 查询最大的企业ID
    EnterpriseVO maxEnterpriseVO = enterpriseDao.findMaxEnterpriseId();
    enterpriseVO.setId(String.valueOf(Long.valueOf(maxEnterpriseVO.getId()) + 1));
    return enterpriseDao.createEnterpriseMerchant(enterpriseVO);
  }

  @Override
  public int updateEnterpriseMerchant(String enterprise_id, EnterpriseVO enterpriseVO) {
    enterpriseVO.setId(enterprise_id);
    return enterpriseDao.updateEnterpriseMerchant(enterpriseVO);
  }

  @Override
  public int deleteEnterpriseMerchant(String enterprise_id) throws RcmsApplicationException {
    return enterpriseDao.deleteEnterpriseMerchant(enterprise_id);
  }

  @Override
  public List<EnterpriseVO> findEnterpriseList(String enterpriseId, Subject subject) {
    EnterpriseVO enterpriseVO = new EnterpriseVO();
    List<EnterpriseVO> list;
    // 如果没有查询企业ID，则获取当前登录用户的企业ID
    if (StringUtils.isEmpty(enterpriseId)) {
      enterpriseVO.setId(UserUtil.getEnterpriseId(subject));
      list = this.findEnterpriseList(enterpriseVO);
    } else {
      list = new ArrayList<>();
      enterpriseVO.setId(enterpriseId);
      list.add(enterpriseVO);
    }
    return list;
  }

}
