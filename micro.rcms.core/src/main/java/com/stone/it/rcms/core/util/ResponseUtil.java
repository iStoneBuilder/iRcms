package com.stone.it.rcms.core.util;

import com.alibaba.fastjson2.JSONObject;
import java.util.Date;
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
        if (code != HttpStatus.SC_OK) {
            return responseBuild(code, desc, "");
        }
        JSONObject result = new JSONObject();
        result.put("status", code);
        result.put("message", desc);
        result.put("timestamp", System.currentTimeMillis());
        return result;
    }

    public static JSONObject responseBuild(int code, String desc, String error) {
        JSONObject result = new JSONObject();
        result.put("status", code);
        result.put("message", desc);
        result.put("timestamp", System.currentTimeMillis());
        JSONObject data = new JSONObject();
        data.put("timestamp", String.valueOf(new Date()));
        data.put("status", code);
        data.put("error", error);
        return responseBuild(code, desc, data);
    }

    public static JSONObject responseBuild(int code, String desc, JSONObject data) {
        JSONObject result = new JSONObject();
        result.put("status", code);
        result.put("message", desc);
        result.put("data", data);
        result.put("timestamp", System.currentTimeMillis());
        return result;
    }
}
