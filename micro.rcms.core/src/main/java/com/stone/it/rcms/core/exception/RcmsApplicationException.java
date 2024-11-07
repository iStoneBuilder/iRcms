package com.stone.it.rcms.core.exception;

/**
 * @author cj.stone
 * @Desc
 */
public class RcmsApplicationException extends RuntimeException {

    public int code = 500;
    public String message;
    public Object error;

    public RcmsApplicationException() {}

    public RcmsApplicationException(int code, String desc) {
        this.code = code;
        this.message = desc;
        this.error = new Object();
    }

    public RcmsApplicationException(int code, String desc, Object error) {
        this.code = code;
        this.message = desc;
        this.error = error;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public RcmsApplicationException setMessage(String message) {
        this.message = message;
        return this;
    }

    public Object getError() {
        return error;
    }

    public RcmsApplicationException setError(Object error) {
        this.error = error;
        return this;
    }
}
