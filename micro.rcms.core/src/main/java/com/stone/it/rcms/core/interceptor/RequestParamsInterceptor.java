package com.stone.it.rcms.core.interceptor;

import com.stone.it.rcms.core.exception.RcmsApplicationException;
import com.stone.it.rcms.core.util.JwtUtils;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
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
                handleRequestParams(message, verify);
            }
        }
    }

    private void handleRequestParams(Message message, Map<String, String> verify) {
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
            if (queryString != null && !queryString.isEmpty()) {
                if (!queryString.contains("enterpriseId")) {
                    newQueryString += "&enterpriseId=" + verify.get("enterpriseId");
                }
                if (!queryString.contains("createBy")) {
                    newQueryString += "&createBy=" + verify.get("account");
                }
                if (!queryString.contains("updateBy")) {
                    newQueryString += "&updateBy=" + verify.get("account");
                }
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
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            // Modify the request body as needed
            String modifiedRequestBody = requestBody.replace("oldValue", "newValue");

            // Set the modified request body back to the message
            InputStream modifiedInputStream =
                new ByteArrayInputStream(modifiedRequestBody.getBytes(StandardCharsets.UTF_8));
            message.setContent(InputStream.class, modifiedInputStream);
        }
    }
}
