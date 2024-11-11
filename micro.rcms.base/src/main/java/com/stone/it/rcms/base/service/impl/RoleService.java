package com.stone.it.rcms.base.service.impl;

import com.alibaba.fastjson2.JSONObject;
import com.stone.it.rcms.base.dao.IRoleDao;
import com.stone.it.rcms.base.service.IRoleService;
import com.stone.it.rcms.base.vo.RoleVO;
import com.stone.it.rcms.core.util.TreeUtil;
import com.stone.it.rcms.core.util.UserUtil;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;
import org.apache.cxf.common.util.StringUtils;
import org.apache.shiro.SecurityUtils;

/**
 * 角色配置
 *
 * @author cj.stone
 * @Desc
 */
@Named
public class RoleService implements IRoleService {

  @Inject
  private IRoleDao roleDao;


  @Override
  public List<RoleVO> findRoleList(RoleVO roleVO) {
    List<RoleVO> treeList = this.findRoleTree(roleVO);
    List<RoleVO> resultList = new ArrayList<>();
    treeList.forEach(role -> {
      JSONObject node = TreeUtil.treeToList(role);
      RoleVO nodeVO = JSONObject.parseObject(JSONObject.toJSONString(node), RoleVO.class);
      resultList.add(nodeVO);
    });
    return resultList;
  }

  @Override
  public List<RoleVO> findRoleTree(RoleVO roleVO) {
    String enterpriseId = roleVO.getCode();
    if (StringUtils.isEmpty(enterpriseId)) {
      enterpriseId = UserUtil.getCurrentByKey(SecurityUtils.getSubject(), "enterpriseId");
    }
    List<RoleVO> rootList = roleDao.findRoleByEnterpriseId(enterpriseId);
    List<RoleVO> allRoles = roleDao.findAllRoles();
    List<RoleVO> treeList = new ArrayList<>();
    rootList.forEach(role -> {
      JSONObject tree = TreeUtil.buildTree(role, allRoles);
      RoleVO iRole = JSONObject.parseObject(tree.toJSONString(), RoleVO.class);
      treeList.add(iRole);
    });
    return treeList;
  }


  @Override
  public int createRole(RoleVO roleVO) {
    //roleVO.setRoleId(UUIDUtil.getUuid());
    return roleDao.createRole(roleVO);
  }

  @Override
  public int updateRole(String roleId, RoleVO roleVO) {
    //roleVO.setRoleId(roleId);
    return roleDao.updateRole(roleVO);
  }

  @Override
  public int deleteRole(String roleId) {
    return roleDao.deleteRole(roleId);
  }
}
