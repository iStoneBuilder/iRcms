package com.stone.it.rcms.core.util;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;

/**
 * @author cj.stone
 * @Date 2024/11/7
 * @Desc
 */
public class TreeUtil {

    public static <T> JSONObject buildTree(T root, T list, boolean isMenu) {
        JSONObject rootNode = JSONObject.parseObject(JSONObject.toJSONString(root));
        JSONArray listArray = JSONArray.parseArray(JSONArray.toJSONString(list));
        for (int i = 0; i < listArray.size(); i++) {
            JSONObject item = listArray.getJSONObject(i);
            // 菜单时构建meta信息
            if (isMenu) {
                buildMenuMeta(item);
            }
            if (item.getString("parentId").equals(rootNode.getString("id"))) {
                JSONArray children = rootNode.getJSONArray("children");
                if (children == null) {
                    children = new JSONArray();
                }
                children.add(item);
                rootNode.put("children", children);
            } else {
                addChildToParent(rootNode, item);
            }
        }
        return rootNode;
    }

    /**
     * --meta-- title icon extraIcon rank activePath showLink roles auths
     */
    private static void buildMenuMeta(JSONObject item) {
        JSONObject meta = new JSONObject();
        meta.put("title", item.getString("title"));
        meta.put("icon", item.getString("icon"));
        meta.put("extraIcon", item.getString("extraIcon"));
        meta.put("rank", item.getIntValue("rankSort"));
        meta.put("activePath", item.getString("activePath"));
        meta.put("showLink", item.getBoolean("showLink"));
        meta.put("roles", (item.getString("roles")).split(","));
        meta.put("auths", (item.getString("auths")).split(","));
        item.put("meta", meta);
    }

    public static <T> JSONObject buildTree(T root, T list) {
        return buildTree(root, list, false);
    }

    private static void addChildToParent(JSONObject parent, JSONObject item) {
        if (parent.getJSONArray("children") == null) {
            return;
        }
        for (Object object : parent.getJSONArray("children")) {
            JSONObject child = (JSONObject)object;
            if (child.getString("id").equals(item.getString("parentId"))) {
                JSONArray children = child.getJSONArray("children");
                if (children == null) {
                    children = new JSONArray();
                }
                children.add(item);
                child.put("children", children);
                return;
            } else {
                addChildToParent(child, item);
            }
        }
    }

    public static <T> JSONObject treeToList(T root) {
        JSONArray itemList = new JSONArray();
        traverseTreeToList(JSONObject.parseObject(JSONObject.toJSONString(root)), itemList);
        JSONObject result = new JSONObject();
        result.put("children", itemList);
        return result;
    }

    private static void traverseTreeToList(JSONObject node, JSONArray itemList) {
        JSONArray children = node.getJSONArray("children");
        node.remove("children");
        itemList.add(node);
        if (children != null && children.size() > 0) {
            for (Object root : children) {
                traverseTreeToList((JSONObject)root, itemList);
            }
        }
    }

}
