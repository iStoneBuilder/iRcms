package com.stone.it.rcms.core.vo;

import com.alibaba.fastjson2.JSONObject;

import java.util.List;
import lombok.Data;

/**
 * @author cj.stone
 * @Desc
 */
@Data
public class MenuVO extends BaseVO {

    private String parentId;
    private String id;
    private String title;
    // 菜单名称
    private String name;
    // 路由名称 （对应路由页面的name属性）
    private String path;
    // 路由地址
    private String component;
    // 组件地址 (对应路由页面的component，不配置为path）
    private int rankSort;
    // 排序
    private String redirect;
    // 重定向地址
    private String icon;
    // 图标
    private String extraIcon;
    // 额外图标
    private String activePath;
    // 当前激活的路由地址(不显示菜单时激活的菜单地址)
    private String keepAlive;
    // 是否缓存页面
    private String roles;
    // 权限角色(栏目权限)
    private String auths;
    // 权限标识(按钮权限)
    private String showLink = "false";
    // 是否在菜单中显示 （false：显示，true：不显示）

    private List<MenuVO> children;
    // 子菜单 (path,name,component)
    private JSONObject meta;
    // 元数据 (title icon extraIcon rank activePath showLink roles auths keepAlive)

}
