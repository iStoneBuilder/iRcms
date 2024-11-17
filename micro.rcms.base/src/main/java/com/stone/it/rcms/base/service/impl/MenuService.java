package com.stone.it.rcms.base.service.impl;

import com.alibaba.fastjson2.JSONObject;
import com.stone.it.rcms.base.dao.IMenuDao;
import com.stone.it.rcms.base.service.IMenuService;
import com.stone.it.rcms.base.vo.MenuVO;
import com.stone.it.rcms.core.exception.RcmsApplicationException;
import com.stone.it.rcms.core.util.TreeUtil;
import com.stone.it.rcms.core.util.UUIDUtil;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;
import org.apache.cxf.common.util.StringUtils;

/**
 * 栏目配置
 *
 * @author cj.stone
 * @Desc
 */
@Named
public class MenuService implements IMenuService {

    @Inject
    private IMenuDao menuDao;

    @Override
    public List<MenuVO> getMenuList(MenuVO menuVO) {
        return menuDao.getMenuList(menuVO);
    }

    @Override
    public List<MenuVO> getMenuTreeList() {
        MenuVO menuVO = new MenuVO();
        menuVO.setId("0");
        List<MenuVO> list = menuDao.getMenuList(new MenuVO());
        JSONObject tree = TreeUtil.buildTree(menuVO, list, true);
        MenuVO rootMenu = JSONObject.parseObject(JSONObject.toJSONString(tree), MenuVO.class);
        return rootMenu.getChildren();
    }

    @Override
    public int createMenu(MenuVO menuVO) {
        // 没有选择根节点，则默认为根节点
        if (StringUtils.isEmpty(menuVO.getParentId())) {
            menuVO.setParentId("0");
        }
        menuVO.setId(UUIDUtil.getUuid());
        return menuDao.createMenu(menuVO);
    }

    @Override
    public int updateMenu(String id, MenuVO menuVO) {
        menuVO.setId(id);
        return menuDao.updateMenu(menuVO);
    }

    @Override
    public int deleteMenu(String id) {
        List<MenuVO> list = menuDao.findMenuListByPid(id);
        if (list.size() > 0) {
            throw new RcmsApplicationException(500, "该菜单下存在子菜单，请先删除子菜单！");
        }
        return menuDao.deleteMenu(id);
    }
}
