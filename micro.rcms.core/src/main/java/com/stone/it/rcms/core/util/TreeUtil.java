package com.stone.it.rcms.core.util;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;

/**
 *
 * @author cj.stone
 * @Date 2024/11/7
 * @Desc
 */
public class TreeUtil {

    public static <T> JSONObject buildTree(T root, T list) {
        JSONObject rootNode = (JSONObject)root;
        JSONArray listArray = (JSONArray)list;
        for (int i = 0; i < listArray.size(); i++) {
            JSONObject item = listArray.getJSONObject(i);
            if (item.getLong("parentId") == rootNode.getLong("id")) {
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

    private static void addChildToParent(JSONObject parent, JSONObject item) {
        if (parent.getJSONArray("children") == null) {
            return;
        }
        for (Object object : parent.getJSONArray("children")) {
            JSONObject child = (JSONObject)object;
            if (child.getLong("id") == item.getLong("parentId")) {
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

}
