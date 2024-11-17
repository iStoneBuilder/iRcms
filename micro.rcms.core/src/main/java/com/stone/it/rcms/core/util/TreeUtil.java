package com.stone.it.rcms.core.util;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import org.apache.cxf.common.util.StringUtils;

/**
 * @author cj.stone
 * @Date 2024/11/7
 * @Desc
 */
public class TreeUtil {

    /**
     * --meta-- title icon extraIcon rank activePath showLink roles auths
     */
    private static final String[] META_KEYS =
        {"title", "icon", "extraIcon", "rank", "activePath", "showLink", "roles", "auths"};
    private static final String[] NODE_KEYS = {"parentId", "id", "path", "name", "redirect", "component"};

    public static <T> JSONObject buildTree(T root, T list) {
        JSONObject rootNode = JSONObject.parseObject(JSONObject.toJSONString(root));
        JSONArray listArray = JSONArray.parseArray(JSONArray.toJSONString(list));
        for (int i = 0; i < listArray.size(); i++) {
            JSONObject item = listArray.getJSONObject(i);
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

    public static <T> JSONObject buildRouterTree(T root, T list) {
        // 处理根节点
        JSONObject routerNode =
            buildRouterNode(JSONObject.parseObject(JSONObject.toJSONString(root)), new JSONObject());
        // 循环处理子节点
        JSONArray listArray = JSONArray.parseArray(JSONArray.toJSONString(list));
        listArray.stream().forEach(item -> {
            // 处理子节点
            JSONObject routerItem = buildRouterNode((JSONObject)item, new JSONObject());
            if (routerItem.getString("parentId").equals(routerNode.getString("id"))) {
                JSONArray children = routerNode.getJSONArray("children");
                if (children == null) {
                    children = new JSONArray();
                }
                children.add(routerItem);
                routerNode.put("children", children);
            } else {
                addChildToRouterParent(routerNode, routerItem);
            }
        });
        return routerNode;
    }

    private static void addChildToRouterParent(JSONObject parent, JSONObject item) {
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
                addChildToRouterParent(child, item);
            }
        }
    }

    private static JSONObject buildRouterNode(JSONObject node, JSONObject routerNode) {
        for (String key : NODE_KEYS) {
            if (node.containsKey(key) && !StringUtils.isEmpty(node.getString(key))) {
                routerNode.put(key, node.get(key));
            }
        }
        // 处理meta信息
        buildRouterMeta(routerNode, node);
        return routerNode;
    }

    private static void buildRouterMeta(JSONObject routerNode, JSONObject node) {
        JSONObject meta = new JSONObject();
        for (String key : META_KEYS) {
            if (node.containsKey(key) && !StringUtils.isEmpty(node.getString(key))) {
                if (key.equals("roles") || key.equals("auths")) {
                    meta.put(key, (node.getString(key)).split(","));
                    continue;
                }
                if (key.equals("rank")) {
                    meta.put(key, node.getIntValue("rankSort"));
                    continue;
                }
                if (key.equals("showLink")) {
                    meta.put(key, node.getBoolean(key));
                    continue;
                }
                meta.put(key, node.get(key));
            }
        }
        routerNode.put("meta", meta);
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
