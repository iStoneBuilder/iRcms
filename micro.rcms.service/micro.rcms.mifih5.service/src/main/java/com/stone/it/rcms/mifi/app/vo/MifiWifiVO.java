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
public class MifiWifiVO extends BaseVO {
    private String operatorId;
    private String wifiName;
    private String wifiPwd;
}
