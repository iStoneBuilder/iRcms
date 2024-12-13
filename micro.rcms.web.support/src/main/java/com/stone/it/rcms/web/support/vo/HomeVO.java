package com.stone.it.rcms.web.support.vo;

import com.alibaba.fastjson2.JSONObject;
import com.stone.it.rcms.core.vo.BaseVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 *
 * @author cj.stone
 * @Date 2024/12/13
 * @Desc
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class HomeVO extends BaseVO {

    private String homeId;

    private List<JSONObject> routeData;
}
