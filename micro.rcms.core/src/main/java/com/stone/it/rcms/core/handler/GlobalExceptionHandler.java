package com.stone.it.rcms.core.handler;

import com.stone.it.rcms.core.exception.RcmsApplicationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 *
 * @author cj.stone
 * @Date 2024/11/5
 * @Desc
 */

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RcmsApplicationException.class)
    public ResponseEntity<Object> handleAllExceptions(Exception ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}