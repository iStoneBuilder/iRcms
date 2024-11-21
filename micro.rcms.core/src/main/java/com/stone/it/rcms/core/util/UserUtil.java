package com.stone.it.rcms.core.util;

import com.alibaba.fastjson2.JSONObject;
import com.stone.it.rcms.core.exception.RcmsApplicationException;
import java.util.Map;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;

/**
 * @author cj.stone
 * @Date 2024/11/11
 * @Desc
 */
public class UserUtil {

    public static String getEnterpriseId(Subject subject) {
        JSONObject loginInfo = getLoginInfo(subject);
        if (loginInfo.containsKey("enterpriseId") && loginInfo.get("enterpriseId") != null) {
            return loginInfo.getString("enterpriseId");
        } else {
            throw new RcmsApplicationException(500, "服务异常，请联系管理员！", "无法获取登录用户的企业ID！");
        }
    }

    public static JSONObject getLoginInfo(Subject subject) {
        JSONObject userInfo;
        try {
            PrincipalCollection principals = subject.getPrincipals();
            if (principals == null) {
                throw new RcmsApplicationException(500, "用户未登录！");
            }
            Map<String, String> user = (Map<String, String>)principals.getPrimaryPrincipal();
            userInfo = JSONObject.parseObject(JSONObject.toJSONString(user));
        } catch (Exception e) {
            throw new RcmsApplicationException(500, "服务异常，请联系管理员！", e);
        }
        return userInfo;
    }

}
