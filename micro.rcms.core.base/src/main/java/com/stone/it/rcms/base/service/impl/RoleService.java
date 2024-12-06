package com.stone.it.rcms.base.service.impl;

import com.alibaba.fastjson2.JSONObject;
import com.stone.it.rcms.base.dao.IPermissionDao;
import com.stone.it.rcms.base.dao.IRoleDao;
import com.stone.it.rcms.base.service.IRoleService;
import com.stone.it.rcms.base.vo.RoleVO;
import com.stone.it.rcms.core.util.TreeUtil;
import com.stone.it.rcms.core.util.UUIDUtil;
import com.stone.it.rcms.core.util.UserUtil;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;

import com.stone.it.rcms.core.vo.PermissionVO;
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

    @Inject
    private IPermissionDao permissionDao;

    private static void handleTree2List(List<RoleVO> treeList, List<RoleVO> resultList) {
        treeList.forEach(role -> {
            JSONObject node = TreeUtil.treeToList(role);
            RoleVO nodeVO = JSONObject.parseObject(JSONObject.toJSONString(node), RoleVO.class);
            resultList.addAll(nodeVO.getChildren());
        });
    }

    @Override
    public List<RoleVO> findRoleList(RoleVO roleVO) {
        List<RoleVO> treeList = this.findRoleTree(roleVO);
        List<RoleVO> resultList = new ArrayList<>();
        handleTree2List(treeList, resultList);
        return resultList;
    }

    @Override
    public List<RoleVO> findEnterPriseRoleList(RoleVO roleVO) {
        return roleDao.findRoleByEnterpriseId(roleVO.getEnterpriseId());
    }

    @Override
    public List<PermissionVO> findRolePermissionList(String roleId) {
        RoleVO roleVO = roleDao.findRoleDetail(roleId);
        return permissionDao.findRolePermissionList(roleVO);
    }

    @Override
    public List<RoleVO> findRoleTree(RoleVO roleVO) {
        String enterpriseId = roleVO.getEnterpriseId();
        if (StringUtils.isEmpty(enterpriseId)) {
            enterpriseId = UserUtil.getEnterpriseId();
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
    public int createRolePermission(String roleId, List<PermissionVO> permissionList) {
        RoleVO roleVO = roleDao.findRoleDetail(roleId);
        // 删除权限角色关联表
        permissionDao.deleteRolePermission(roleVO.getCode());
        // 创建权限角色关联表
        permissionDao.createRolePermission(permissionList, roleVO.getCode(), UserUtil.getUserId());
        // 查询角色的下级角色列表
        List<RoleVO> treeList = findRoleTree(roleVO);
        List<RoleVO> roleList = new ArrayList<>();
        for (RoleVO vo : treeList) {
            if (vo.getCode().equals(roleVO.getCode())) {
                if (vo.getChildren() != null) {
                    handleTree2List(vo.getChildren(), roleList);
                }
                break;
            }
        }
        // 清除下级角色 在上级角色中不存在的权限
        if (!roleList.isEmpty()) {
            permissionDao.deleteRolePermissionNotExist(permissionList, roleList);
        }
        return 1;
    }

    @Override
    public int createRole(RoleVO roleVO) {
        roleVO.setId(UUIDUtil.getUuid());
        return roleDao.createRole(roleVO);
    }

    @Override
    public int updateRole(String roleId, RoleVO roleVO) {
        roleVO.setId(roleId);
        return roleDao.updateRole(roleVO);
    }

    @Override
    public int deleteRole(String roleId) {
        return roleDao.deleteRole(roleId);
    }
}
