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
