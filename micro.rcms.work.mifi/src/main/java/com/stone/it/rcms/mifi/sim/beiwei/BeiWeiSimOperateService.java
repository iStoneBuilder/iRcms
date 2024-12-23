package com.stone.it.rcms.mifi.sim.beiwei;

import com.alibaba.fastjson2.JSONObject;
import com.stone.it.rcms.core.http.RequestUtil;
import com.stone.it.rcms.core.http.ResponseEntity;
import com.stone.it.rcms.mifi.sim.vo.CarrierVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author cj.stone
 * @Date 2024/12/20
 * @Desc
 */
public class BeiWeiSimOperateService extends BeiWeiBaseService {

    private static final Logger logger = LoggerFactory.getLogger(BeiWeiSimOperateService.class);
    private static final String FLOW_MONTH_NOW_ENDPOINT = "/query/flowMonthNow";
    private static final String FLOW_DAY_ENDPOINT = "/query/flowDay";
    private static final String HANDLE_ENDPOINT = "/handle/";
    private static final String REAL_NAME_URL_ENDPOINT = "/query/getRealNameUrl";
    private static final String CARD_INFO_ENDPOINT = "/query/cardInfo";
    private static final String AUTH_ENDPOINT = "/handle/auth";
    private static final String QUERY_CARD_STATUS = "/query/cardStatus";

    public static JSONObject queryMonthFlow(String iccid, String month, CarrierVO carrierVO) {
        JSONObject body = buildBaseBody(iccid);
        body.put("month", month);
        return sendPostRequest(carrierVO.getAddress() + FLOW_MONTH_NOW_ENDPOINT, body, carrierVO);
    }

    public static Double queryDayFlow(String iccid, String day, CarrierVO carrierVO) {
        JSONObject body = buildBaseBody(iccid);
        body.put("dayStart", day);
        body.put("dayEnd", day);
        JSONObject response = sendPostRequest(carrierVO.getAddress() + FLOW_DAY_ENDPOINT, body, carrierVO);
        if (response != null && !response.getJSONArray("dayFlows").isEmpty()) {
            return response.getJSONArray("dayFlows").getJSONObject(0).getDouble("used");
        }
        return null;
    }

    public static String operate(String iccid, String operateType, CarrierVO carrierVO) {
        JSONObject body = buildBaseBody(iccid);
        JSONObject response = sendPostRequest(carrierVO.getAddress() + HANDLE_ENDPOINT + operateType, body, carrierVO);
        if (response != null && response.containsKey("serviceRequestId")) {
            return response.getString("serviceRequestId");
        }
        return null;
    }

    public static JSONObject queryRealNameUrl(String iccid, CarrierVO carrierVO) {
        return sendPostRequest(carrierVO.getAddress() + REAL_NAME_URL_ENDPOINT, buildBaseBody(iccid), carrierVO);
    }

    public static JSONObject queryCardInfo(String iccid, CarrierVO carrierVO) {
        return sendPostRequest(carrierVO.getAddress() + CARD_INFO_ENDPOINT, buildBaseBody(iccid), carrierVO);
    }

    public static JSONObject queryRealNameStatus(String iccid, CarrierVO carrierVO) {
        return sendPostRequest(carrierVO.getAddress() + AUTH_ENDPOINT, buildBaseBody(iccid), carrierVO);
    }

    public static JSONObject queryCardStatus(String iccid, CarrierVO carrierVO) {
        return sendPostRequest(carrierVO.getAddress() + QUERY_CARD_STATUS, buildBaseBody(iccid), carrierVO);
    }

    private static JSONObject sendPostRequest(String url, JSONObject body, CarrierVO carrierVO) {
        try {
            ResponseEntity responseEntity = RequestUtil.doPost(url, body.toJSONString(), buildHeader(carrierVO));
            return getResBody(responseEntity);
        } catch (Exception e) {
            logger.error("Failed to send POST request to URL: {}", url, e);
            return null;
        }
    }

    public static JSONObject getResBody(ResponseEntity responseEntity) {
        if (responseEntity != null && "200".equals(responseEntity.getCode())) {
            return getSimResBody(JSONObject.parseObject(responseEntity.getBody()));
        }
        return null;
    }

}
