package com.stone.it.rcms.web.support.dao;

import com.stone.it.rcms.core.vo.MenuVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *
 * @author cj.stone
 * @Date 2024/12/13
 * @Desc
 */
public interface IHomeDao {

    List<MenuVO> findTopRouterData(@Param("parentId") String parentId);

    List<MenuVO> findUserRouterData(@Param("userId") String userId);

}
