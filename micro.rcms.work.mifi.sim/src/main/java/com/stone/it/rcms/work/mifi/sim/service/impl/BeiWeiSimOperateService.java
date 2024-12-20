package com.stone.it.rcms.work.mifi.sim.service.impl;

import com.alibaba.fastjson2.JSONObject;
import com.stone.it.rcms.core.http.RequestUtil;
import com.stone.it.rcms.core.http.ResponseEntity;
import com.stone.it.rcms.work.mifi.sim.service.IBeiWeiSimOperateService;

/**
 *
 * @author cj.stone
 * @Date 2024/12/20
 * @Desc
 */
public class BeiWeiSimOperateService extends BeiWeiBaseService implements IBeiWeiSimOperateService {

    @Override
    public ResponseEntity operate(String iccid, String operateType, JSONObject authInfo) {
        // openDevice(stopDevice)
        String url = authInfo.getString("address") + "/handle/" + operateType;
        JSONObject body = buildBaseBody(iccid);
        return RequestUtil.doPost(url, body.toJSONString(), buildHeader(authInfo));
    }

    @Override
    public ResponseEntity queryCardInfo(String iccid, JSONObject authInfo) {
        String url = authInfo.getString("address") + "/query/cardInfo";
        return RequestUtil.doPost(url, buildBaseBody(iccid).toJSONString(), buildHeader(authInfo));
    }

    @Override
    public ResponseEntity queryDayFlow(String iccid, String day, JSONObject authInfo) {
        String url = authInfo.getString("address") + "/query/flowDay";
        JSONObject body = buildBaseBody(iccid);
        body.put("dayStart", day);
        body.put("dayEnd", day);
        return RequestUtil.doPost(url, body.toJSONString(), buildHeader(authInfo));
    }

    @Override
    public ResponseEntity queryMonthFlow(String iccid, String month, JSONObject authInfo) {
        String url = authInfo.getString("address") + "/query/flowMonthNow";
        JSONObject body = buildBaseBody(iccid);
        body.put("month", month);
        return RequestUtil.doPost(url, body.toJSONString(), buildHeader(authInfo));
    }

    @Override
    public ResponseEntity queryRealNameUrl(String iccid, JSONObject authInfo) {
        String url = authInfo.getString("address") + "/query/getRealNameUrl";
        return RequestUtil.doPost(url, buildBaseBody(iccid).toJSONString(), buildHeader(authInfo));
    }

    @Override
    public ResponseEntity queryRealNameStatus(String iccid, JSONObject authInfo) {
        String url = authInfo.getString("address") + "/handle/auth";
        return RequestUtil.doPost(url, buildBaseBody(iccid).toJSONString(), buildHeader(authInfo));
    }
}
