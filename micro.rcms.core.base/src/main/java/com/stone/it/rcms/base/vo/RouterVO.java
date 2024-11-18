package com.stone.it.rcms.base.vo;

import com.alibaba.fastjson2.JSONObject;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;
import lombok.Data;

/**
 *
 * @author cj.stone
 * @Date 2024/11/17
 * @Desc
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RouterVO {

    private String parentId;
    private String id;
    private String name; // 路由名称 （对应路由页面的name属性）
    private String path; // 路由地址
    private String component; // 组件地址 (对应路由页面的component，不配置为path）
    private String redirect; // 重定向地址

    private List<MenuVO> children; // 子菜单 (path,name,component)
    private JSONObject meta; // 元数据 (title,roles,showLink,icon,auths,rank,activePath)

}
