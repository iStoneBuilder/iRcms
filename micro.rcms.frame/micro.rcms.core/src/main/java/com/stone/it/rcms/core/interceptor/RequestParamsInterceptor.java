package com.stone.it.rcms.core.interceptor;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.stone.it.rcms.core.util.JwtUtils;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author cj.stone
 * @Date 2024/11/7
 * @Desc
 */
public class RequestParamsInterceptor extends AbstractPhaseInterceptor<Message> {

    private static final Logger LOGGER = LoggerFactory.getLogger(RequestParamsInterceptor.class);

    public RequestParamsInterceptor(String receive) {
        super(Phase.PRE_PROTOCOL);
    }

    @Override
    public void handleMessage(Message message) throws Fault {
        LOGGER.info("****** CXF Interceptor In params, handle...START");
        // 获取请求头参数
        @SuppressWarnings("unchecked")
        TreeMap<String, List<String>> headers = (TreeMap<String, List<String>>)message.get(Message.PROTOCOL_HEADERS);
        if (headers.containsKey("Authorization")) {
            ArrayList<String> token = (ArrayList<String>)headers.get("Authorization");
            // 校验Token合法性
            Map<String, String> verify = JwtUtils.getTokenInfo(token.get(0));
            // 校验Token是否合法
            if (!verify.isEmpty()) {
                try {
                    handleRequestParams(message, verify);
                } catch (IOException e) {
                    // throw new RuntimeException(e);
                }
            }
        }
    }

    private void handleRequestParams(Message message, Map<String, String> verify) throws IOException {
        // 获取请求方法类型
        String methodType = (String)message.get(Message.HTTP_REQUEST_METHOD);
        LOGGER.info("****** CXF Interceptor In params, MethodType...{}", methodType);
        if ("GET".equalsIgnoreCase(methodType)) {
            // 设置请求参数
            String queryString = (String)message.get(Message.QUERY_STRING);
            LOGGER.info("****** CXF Interceptor In params, Params...{}", queryString);
            String newQueryString = "";
            // 增加租户ID
            newQueryString = "tenantId=" + verify.get("tenantId");
            newQueryString += "&currentUserId=" + verify.get("account");
            newQueryString += "&currentEnterpriseId=" + verify.get("enterpriseId");
            if (queryString != null && !queryString.isEmpty()) {
                newQueryString += "&" + queryString;
            }
            message.put(Message.QUERY_STRING, newQueryString);
            LOGGER.info("****** CXF Interceptor In params, New Params...{}", message.get(Message.QUERY_STRING));
        } else {
            // 修改请求体
            InputStream inputStream = message.getContent(InputStream.class);
            String requestBody = null;
            try {
                requestBody = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
                LOGGER.info("****** CXF Interceptor In params, Body...{}", requestBody);
                // Modify the request body as needed
                if (!requestBody.isEmpty()) {
                    String newRequestBody = requestBody;
                    if (requestBody.startsWith("{")) {
                        newRequestBody = buildNewRequestBody(JSONObject.parseObject(requestBody), verify);
                    } else if (requestBody.startsWith("[")) {
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append("[");
                        JSONArray array = JSONArray.parseArray(requestBody);
                        for (int i = 0; i < array.size(); i++) {
                            stringBuilder.append(buildNewRequestBody(array.getJSONObject(i), verify));
                            if (i < array.size() - 1) {
                                stringBuilder.append(",");
                            }
                        }
                        stringBuilder.append("]");
                        newRequestBody = stringBuilder.toString();
                    }
                    // Set the modified request body back to the message
                    assert newRequestBody != null;
                    InputStream modifiedInputStream =
                        new ByteArrayInputStream(newRequestBody.getBytes(StandardCharsets.UTF_8));
                    message.setContent(InputStream.class, modifiedInputStream);
                    LOGGER.info("****** CXF Interceptor In params, New Body...{}", newRequestBody);
                }
            } catch (IOException e) {
                inputStream.close();
            } finally {
                inputStream.close();
            }
        }
    }

    private String buildNewRequestBody(JSONObject jsonObject, Map<String, String> verify) {
        // 检查输入参数是否为 null
        if (jsonObject == null) {
            throw new IllegalArgumentException("jsonObject cannot be null");
        }
        if (verify == null) {
            throw new IllegalArgumentException("verify cannot be null");
        }

        // 定义一个辅助方法来处理键的存在性和值的填充
        fillIfAbsent(jsonObject, "tenantId", verify.get("tenantId"));
        fillIfAbsent(jsonObject, "currentEnterpriseId", verify.get("enterpriseId"));
        fillIfAbsent(jsonObject, "currentUserId", verify.get("account"));

        // 返回 JSON 字符串
        try {
            return JSONObject.toJSONString(jsonObject);
        } catch (Exception e) {
            throw new RuntimeException("Failed to convert JSONObject to string", e);
        }
    }

    // 辅助方法：如果键不存在或值为空，则填充默认值
    private void fillIfAbsent(JSONObject jsonObject, String key, String defaultValue) {
        if (defaultValue != null && !defaultValue.trim().isEmpty()
            && (!jsonObject.containsKey(key) || jsonObject.getString(key).trim().isEmpty())) {
            jsonObject.put(key, defaultValue.trim());
        }
    }

}
