package com.stone.it.rcms.work.mifi.sim.service;

import com.alibaba.fastjson2.JSONObject;
import com.stone.it.rcms.core.http.ResponseEntity;

/**
 * 调用卡商相关接口
 * 
 * @author cj.stone
 * @Date 2024/12/20
 * @Desc
 */
public interface IBeiWeiSimOperateService {

    /** 停机/复机 */
    ResponseEntity operate(String iccid, String operateType, JSONObject authInfo);

    /** 查询卡信息 */
    ResponseEntity queryCardInfo(String iccid, JSONObject authInfo);

    /** 查询卡日流量 */
    ResponseEntity queryDayFlow(String iccid, String day, JSONObject authInfo);

    /** 查询卡月流量 */
    ResponseEntity queryMonthFlow(String iccid, String month, JSONObject authInfo);

    /** 查询实名认证地址 */
    ResponseEntity queryRealNameUrl(String iccid, JSONObject authInfo);

    /** 查询卡实名状态 */
    ResponseEntity queryRealNameStatus(String iccid, JSONObject authInfo);

}
