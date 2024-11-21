package com.stone.it.rcms.core.exception;

/**
 *
 * @author cj.stone
 * @Date 2024/10/23
 * @Desc
 */
public enum RcmsExceptionEnum {

    UnauthenticatedException(401, "请求认证已失效"), UnauthorizedException(403, "您没有权限访问该资源"),
    AuthenticationException(500, "");

    private final int code;

    private final String message;

    RcmsExceptionEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;

    }
}
