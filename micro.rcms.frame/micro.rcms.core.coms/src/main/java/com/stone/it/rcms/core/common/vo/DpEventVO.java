package com.stone.it.rcms.core.common.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 *
 * @author cj.stone
 * @Date 2024/12/10
 * @Desc
 */
@Data
@AllArgsConstructor
public class DpEventVO {
    // 事件类型
    private String eventType;
    // 事件内容
    private String eventContext;
}
