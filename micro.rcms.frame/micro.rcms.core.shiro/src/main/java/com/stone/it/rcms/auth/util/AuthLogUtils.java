package com.stone.it.rcms.auth.util;

import com.alibaba.fastjson2.JSONObject;
import com.stone.it.rcms.core.http.RequestUtil;
import com.stone.it.rcms.core.http.ResponseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author cj.stone
 * @Date 2024/11/21
 * @Desc
 */
public class AuthLogUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthLogUtils.class);

    public static String getIpAddr(HttpServletRequest request) {
        String ipAddress = null;
        try {
            ipAddress = request.getHeader("x-forwarded-for");
            if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("Proxy-Client-IP");
            }
            if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("WL-Proxy-Client-IP");
            }
            // 对于通过多个代理的情况，第一个IP为客户端真实IP，多个IP按照','分割
            if (ipAddress != null && ipAddress.length() > 15) {
                // "***.***.***.***".length()
                if (ipAddress.indexOf(",") > 0) {
                    ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
                }
            }
        } catch (Exception e) {
            LOGGER.error("获取IP地址失败", e);
            ipAddress = null;
        }
        return ipAddress;
    }

    public static String getLocation(String ip) {
        if (ip == null || ip.contains("0:0:0:0")) {
            return "未知地点";
        }
        Map<String, String> params = new HashMap<>();
        params.put("lang", "zh-CN");
        params.put("fields", "49177");
        ResponseEntity res = RequestUtil.doGet("http://ip-api.com/json/" + ip, params);
        if ("200".equals(res.getCode())) {
            if (res.getBody().contains("\"status\":\"success\"")) {
                JSONObject result = JSONObject.parseObject(res.getBody());
                return result.getString("country") + " " + result.getString("regionName") + " "
                    + result.getString("city");
            }
        }
        return "未知地点";
    }

    public static String getOs(String userAgent) {
        if (userAgent.toLowerCase().contains("windows")) {
            return "Windows";
        } else if (userAgent.toLowerCase().contains("mac")) {
            return "Mac OS X";
        } else if (userAgent.toLowerCase().contains("x11")) {
            return "Unix";
        } else if (userAgent.toLowerCase().contains("android")) {
            return "Android";
        } else if (userAgent.toLowerCase().contains("iphone")) {
            return "iOS";
        } else if (userAgent.toLowerCase().contains("postman")) {
            return "Postman";
        } else {
            return "Unknown OS";
        }
    }

    public static String getBrowser(String userAgent) {
        if (userAgent.toLowerCase().contains("chrome")) {
            return "Chrome";
        } else if (userAgent.toLowerCase().contains("safari")) {
            return "Safari";
        } else if (userAgent.toLowerCase().contains("firefox")) {
            return "Firefox";
        } else if (userAgent.toLowerCase().contains("msie") || userAgent.toLowerCase().contains("trident")) {
            return "Internet Explorer";
        } else if (userAgent.toLowerCase().contains("postman")) {
            return "Postman";
        } else {
            return "Unknown Browser";
        }
    }
}
