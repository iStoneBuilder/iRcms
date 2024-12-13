package com.stone.it.rcms.base.service.impl;

import com.stone.it.rcms.base.dao.IMenuDao;
import com.stone.it.rcms.base.service.IMenuService;
import com.stone.it.rcms.core.exception.RcmsApplicationException;
import com.stone.it.rcms.core.util.UUIDUtil;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;

import com.stone.it.rcms.core.vo.MenuVO;
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
        if ("".equals(menuVO.getParentId()) || null == menuVO.getParentId()) {
            menuVO.setParentId("0");
        }
        menuVO.setId(id);
        return menuDao.updateMenu(menuVO);
    }

    @Override
    public int deleteMenu(String id) {
        List<MenuVO> list = menuDao.findMenuListByPid(id);
        if (!list.isEmpty()) {
            throw new RcmsApplicationException(500, "该菜单下存在子菜单，请先删除子菜单！");
        }
        return menuDao.deleteMenu(id);
    }
}
