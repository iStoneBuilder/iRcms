package com.stone.it.rcms.mifi.common.vo;

import lombok.Data;

import java.util.List;

/**
 *
 * @author cj.stone
 * @Date 2024/12/2
 * @Desc
 */
@Data
public class CommonVO {

    private String pId;
    private String id;
    private String name;
    private String code;

    private List<CommonVO> children;
}
