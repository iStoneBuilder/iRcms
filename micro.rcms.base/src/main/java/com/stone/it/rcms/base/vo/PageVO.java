package com.stone.it.rcms.base.vo;

import com.stone.it.rcms.base.config.PageConfig;
import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.io.Serializable;

/**
 * @author cj.stone
 * @Date 2022/12/14
 * @Desc
 */
@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class PageVO implements Serializable {

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
    private int pageSize;
    /**
     * 起始
     */
    private int startIndex;
    /**
     * 结束
     */
    private int endIndex;

    PageVO(){
        pageSize = PageConfig.DEFAULT.getDefaultPage();
    }

}
