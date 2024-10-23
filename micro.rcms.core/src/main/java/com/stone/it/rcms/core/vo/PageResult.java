package com.stone.it.rcms.core.vo;

import java.io.Serializable;
import java.util.List;

/**
 * @Author iMrJi
 * @Description TODO
 * @Version 1.0
 */
public final class PageResult<T> implements Serializable {

    private static final long serialVersionUID = -3422814987475041137L;

    private int code = 200;

    private String message = "success";

    /**
     * 分页信息
     */
    private PageVO meta;
    /**
     * 对象list
     */
    private List<T> data;

    public PageResult(PageVO meta, List<T> data) {
        this.meta = meta;
        this.data = data;
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

    public void setMessage(String message) {
        this.message = message;
    }

    public PageVO getMeta() {
        return meta;
    }

    public void setMeta(PageVO meta) {
        this.meta = meta;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
