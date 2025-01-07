package com.stone.it.rcms.admin.service.impl;

import com.alibaba.fastjson2.JSONObject;
import com.stone.it.rcms.core.util.TreeUtil;
import com.stone.it.rcms.core.util.UserUtil;
import com.stone.it.rcms.core.vo.MenuVO;
import com.stone.it.rcms.admin.dao.IHomeDao;
import com.stone.it.rcms.admin.service.IHomeService;
import com.stone.it.rcms.admin.vo.HomeVO;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author cj.stone
 * @Date 2024/12/13
 * @Desc
 */
@Named
public class HomeService implements IHomeService {

    @Inject
    private IHomeDao homeDao;

    @Override
    public HomeVO getHomeData() {
        HomeVO homeData = new HomeVO();
        List<JSONObject> routerData = getRouterData(UserUtil.getUserId());
        homeData.setRouteData(routerData);
        return homeData;
    }

    private List<JSONObject> getRouterData(String userId) {
        List<JSONObject> treeData = new ArrayList<>();
        // 查询根节点数据
        List<MenuVO> rootList = homeDao.findTopRouterData("0");
        List<MenuVO> allList = homeDao.findUserRouterData(userId);
        rootList.forEach(menuVO -> {
            JSONObject tree = TreeUtil.buildRouterTree(menuVO, allList);
            // 存在子节点
            if (tree.containsKey("children")) {
                treeData.add(tree);
            } else {
                if (!(null == tree.getString("component") || Objects.equals(tree.getString("component"), ""))) {
                    treeData.add(tree);
                }
            }
        });
        return treeData;
    }

}
