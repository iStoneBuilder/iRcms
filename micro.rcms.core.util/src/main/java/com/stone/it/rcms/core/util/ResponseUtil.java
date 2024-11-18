package com.stone.it.rcms.core.util;

import com.alibaba.fastjson2.JSONObject;
import org.apache.http.HttpStatus;

/**
 *
 * @author cj.stone
 * @Date 2024/10/23
 * @Desc
 */
public class ResponseUtil {

    public static JSONObject responseBuild(JSONObject data) {
        return responseBuild(HttpStatus.SC_OK, "success", data);
    }

    public static JSONObject responseBuild(int code, String desc) {
        // 统一构建响应
        JSONObject result = new JSONObject();
        result.put("code", code);
        result.put("message", desc);
        result.put("timestamp", System.currentTimeMillis());
        if (code != HttpStatus.SC_OK) {
            result.put("data", new JSONObject().fluentPut("error", ""));
        }
        return result;
    }

    public static JSONObject responseBuild(int code, String desc, String error) {
        JSONObject result = responseBuild(code, desc);
        result.getJSONObject("data").put("error", error);
        return result;
    }

    public static JSONObject responseBuild(int code, String desc, JSONObject data) {
        JSONObject result = responseBuild(code, desc);
        result.put("data", data);
        return result;
    }

    public static JSONObject response(boolean success, JSONObject data) {
        JSONObject result = new JSONObject();
        result.put("success", success);
        result.put("data", data);
        return result;
    }

}
