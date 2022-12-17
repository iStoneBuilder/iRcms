package com.stone.it.rcms.base.config;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author iMrJi
 * @Description TODO
 * @Date 2022/8/26 10:16 PM
 * @Version 1.0
 */
@Data
public class PageConfig implements Serializable {

    private static final long serialVersionUID = 8600747365376219368L;

    public static final PageConfig DEFAULT = new PageConfig();
    /**
     * 默认分页大小
     */
    private Integer defaultPage = 15;
    /**
     * 最大分页大小
     */
    private Integer maxPageSize = 3000;

}
