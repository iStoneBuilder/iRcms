package com.stone.it.rcms.core.handler;

import com.alibaba.fastjson2.JSONObject;
import com.stone.it.rcms.core.util.ResponseUtil;
import org.apache.shiro.ShiroException;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 *
 * @author cj.stone
 * @Date 2024/10/23
 * @Desc
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 处理 Shiro 异常
     * 
     * @param e
     * @return
     */
    @ExceptionHandler(ShiroException.class)
    public ResponseEntity<String> handleShiroException(ShiroException e) {
        // 处理 Shiro 异常的逻辑
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase() + ": " + e.getMessage());
    }

    @ExceptionHandler(UnauthenticatedException.class)
    public ResponseEntity<String> handleUnauthenticatedException(UnauthenticatedException e) {
        JSONObject body = ResponseUtil.responseBuild(HttpStatus.UNAUTHORIZED.value(),
            HttpStatus.UNAUTHORIZED.getReasonPhrase(), e.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(body.toJSONString());
    }

    @ExceptionHandler(AuthorizationException.class)
    public ResponseEntity<String> handleAuthorizationException(AuthorizationException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Shiro 异常: " + e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        // 处理其他异常的逻辑
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("服务器内部错误: " + e.getMessage());
    }
}
