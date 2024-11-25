package com.stone.it.rcms.core.util;

import com.stone.it.rcms.core.exception.RcmsApplicationException;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @author cj.stone
 * @Date 2024/11/11
 * @Desc
 */
public class UserUtil {

    private static final String ERROR_NO_REQUEST_ATTRIBUTES = "无法获取请求上下文！";
    private static final String ERROR_NO_AUTHORIZATION_HEADER = "请求头 'Authorization' 为空或缺失！";
    private static final String ERROR_JWT_PARSING_FAILED = "解析 JWT 失败: ";
    private static final String ERROR_NO_ENTERPRISE_ID = "无法获取登录用户的企业ID！";

    public static String getEnterpriseId() {
        return getCurrentByKey("enterpriseId");
    }

    public static String getTenantId() {
        return getCurrentByKey("tenantId");
    }

    public static String getUserId() {
        return getCurrentByKey("account");
    }

    private static String getCurrentByKey(String enterpriseId) {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null) {
            throw new RcmsApplicationException(500, "服务异常，请联系管理员！", ERROR_NO_REQUEST_ATTRIBUTES);
        }
        HttpServletRequest request = ((ServletRequestAttributes)requestAttributes).getRequest();
        String headerValue = request.getHeader("Authorization");
        if (headerValue == null || headerValue.isEmpty()) {
            throw new RcmsApplicationException(500, "服务异常，请联系管理员！", ERROR_NO_AUTHORIZATION_HEADER);
        }
        try {
            Map<String, String> access = JwtUtils.getTokenInfo(headerValue);
            String current = access.get(enterpriseId);
            if (current != null) {
                return current;
            }
        } catch (Exception e) {
            throw new RcmsApplicationException(500, "服务异常，请联系管理员！", ERROR_JWT_PARSING_FAILED + e.getMessage());
        }
        throw new RcmsApplicationException(500, "服务异常，请联系管理员！", ERROR_NO_ENTERPRISE_ID);
    }
}
