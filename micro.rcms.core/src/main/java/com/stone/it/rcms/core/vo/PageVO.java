package com.stone.it.rcms.core.vo;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author cj.stone
 * @Desc
 */
@Data
public class PageVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 总记录数
     */
    private int totalRows;
    /**
     * 总页面数
     */
    private int totalPage;
    /**
     * 当前页数
     */
    private int curPage;
    /**
     * 每页条数
     */
    private int pageSize = PageConfig.DEFAULT.getDefaultPage();
    /**
     * 起始
     */
    private int startIndex;
    /**
     * 结束
     */
    private int endIndex;

}
