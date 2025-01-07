package com.stone.it.rcms.mifi.common.vo;

import com.alibaba.fastjson2.JSONObject;
import lombok.Data;

/**
 *
 * @author cj.stone
 * @Date 2024/12/22
 * @Desc
 */
@Data
public class SimAuthUrlVO {
    private String iccid;
    private JSONObject realNameInfo;
}
