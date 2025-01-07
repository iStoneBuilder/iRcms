package com.stone.it.rcms.base.dao;

import java.util.List;

import com.stone.it.rcms.base.vo.RoleVO;
import com.stone.it.rcms.core.vo.MenuVO;
import org.apache.ibatis.annotations.Param;

/**
 * @author cj.stone
 * @Desc
 */
public interface IMenuDao {

    List<MenuVO> getMenuList(MenuVO menuVO);

    int createMenu(MenuVO menuVO);

    int updateMenu(MenuVO menuVO);

    int deleteMenu(@Param("id") String id);

    List<MenuVO> findMenuListByPid(@Param("pid") String pid);

    List<MenuVO> findMenuListByRoleCode(RoleVO roleVO);
}
