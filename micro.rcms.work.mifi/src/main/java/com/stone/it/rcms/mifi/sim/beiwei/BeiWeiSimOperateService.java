package com.stone.it.rcms.mifi.sim.beiwei;

import com.alibaba.fastjson2.JSONObject;
import com.stone.it.rcms.core.http.RequestUtil;
import com.stone.it.rcms.core.http.ResponseEntity;
import com.stone.it.rcms.mifi.sim.vo.CarrierVO;

/**
 *
 * @author cj.stone
 * @Date 2024/12/20
 * @Desc
 */
public class BeiWeiSimOperateService extends BeiWeiBaseService {

    public static JSONObject queryMonthFlow(String iccid, String month, CarrierVO carrierVO) {
        JSONObject authInfo = JSONObject.parseObject(JSONObject.toJSONString(carrierVO));
        String url = authInfo.getString("address") + "/query/flowMonthNow";
        JSONObject body = buildBaseBody(iccid);
        body.put("month", month);
        ResponseEntity res = RequestUtil.doPost(url, body.toJSONString(), buildHeader(authInfo));
        return getResBody(res);
    }

    public static Double queryDayFlow(String iccid, String day, CarrierVO carrierVO) {
        JSONObject authInfo = JSONObject.parseObject(JSONObject.toJSONString(carrierVO));
        String url = authInfo.getString("address") + "/query/flowDay";
        JSONObject body = buildBaseBody(iccid);
        body.put("dayStart", day);
        body.put("dayEnd", day);
        ResponseEntity res = RequestUtil.doPost(url, body.toJSONString(), buildHeader(authInfo));
        JSONObject dayFlow = getResBody(res);
        if (dayFlow != null && !dayFlow.getJSONArray("dayFlows").isEmpty()) {
            return dayFlow.getJSONArray("dayFlows").getJSONObject(0).getDouble("used");
        }
        return null;
    }

    public ResponseEntity operate(String iccid, String operateType, JSONObject authInfo) {
        // openDevice(stopDevice)
        String url = authInfo.getString("address") + "/handle/" + operateType;
        JSONObject body = buildBaseBody(iccid);
        return RequestUtil.doPost(url, body.toJSONString(), buildHeader(authInfo));
    }

    public ResponseEntity queryCardInfo(String iccid, JSONObject authInfo) {
        String url = authInfo.getString("address") + "/query/cardInfo";
        return RequestUtil.doPost(url, buildBaseBody(iccid).toJSONString(), buildHeader(authInfo));
    }

    public ResponseEntity queryRealNameUrl(String iccid, JSONObject authInfo) {
        String url = authInfo.getString("address") + "/query/getRealNameUrl";
        return RequestUtil.doPost(url, buildBaseBody(iccid).toJSONString(), buildHeader(authInfo));
    }

    public ResponseEntity queryRealNameStatus(String iccid, JSONObject authInfo) {
        String url = authInfo.getString("address") + "/handle/auth";
        return RequestUtil.doPost(url, buildBaseBody(iccid).toJSONString(), buildHeader(authInfo));
    }
}
