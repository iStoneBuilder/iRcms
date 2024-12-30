package com.stone.it.rcms.mifi.app.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.stone.it.rcms.core.vo.BaseVO;
import lombok.Data;

/**
 *
 * @author cj.stone
 * @Date 2024/12/30
 * @Desc
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MifiControlVO extends BaseVO {
    private String operateId;
    private String deviceSn;
    private String cmd;
    private String param;
    private String source;
}
