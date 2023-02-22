package com.stone.it.micro.rcms.http;

import org.apache.commons.codec.Charsets;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.Set;

/**
 * @author cj.stone
 * @Date 2023/2/21
 * @Desc
 */
public class RequestExecute extends RequestClient {


    private static final Logger LOGGER = LoggerFactory.getLogger(RequestExecute.class);

    /**
     * 请求执行
     *
     * @param client
     * @param methodType
     * @param url
     * @param body
     * @param header
     * @param params
     * @return
     * @throws Exception
     */
    protected static ResponseEntity execute(HttpClient client, RequestType methodType,
                                            String url, String body,
                                            Map<String, String> header, Map<String, String> params) throws Exception {
        // 请求类型
        LOGGER.info("RCMS_REQUEST_TYPE : " + methodType);
        // 处理请求参数
        url = handleUrl(url, params);
        LOGGER.info("RCMS_REQUEST_URL : " + url);
        // 获取Base
        HttpRequestBase requestBase = getBase(methodType, url, body, header);
        // 执行请求
        HttpResponse httpResponse = client.execute(requestBase);
        // 解析数据
        ResponseEntity responseEntity = new ResponseEntity();
        LOGGER.info("RCMS_REQUEST_RESPONSE_CODE : " + httpResponse.getStatusLine().getStatusCode());
        // 设置响应码
        responseEntity.setCode(String.valueOf(httpResponse.getStatusLine().getStatusCode()));
        // 解析响应体
        String response = EntityUtils.toString(httpResponse.getEntity(), Charsets.UTF_8);
        responseEntity.setBody(response);
        LOGGER.info("RCMS_REQUEST_RESPONSE_BODY : " + response);
        if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            responseEntity.setMessage("success");
        } else {
            responseEntity.setMessage("fail");
        }
        return responseEntity;
    }

    protected static String handleUrl(String url, Map<String, String> params) throws Exception {
        StringBuilder uriParams = new StringBuilder();
        Set<Map.Entry<String, String>> paramSet = params.entrySet();
        for (Map.Entry<String, String> next : paramSet) {
            uriParams.append("&").append(next.getKey()).append("=").append(next.getValue());
        }
        String paramUri = uriParams.toString();
        if (!url.contains("?")) {
            paramUri = paramUri.replaceFirst("&", "?");
        }
        LOGGER.info("http request params is : " + paramUri);
        return url + paramUri;
    }

    protected static HttpRequestBase getBase(RequestType methodType, String url, String body, Map<String, String> header) throws UnsupportedEncodingException {
        HttpRequestBase base = null;
        switch (methodType) {
            case POST:
                base = new HttpPost(url);
                if (body != null) {
                    StringEntity httpEntity = new StringEntity(body);
                    ((HttpPost) base).setEntity(httpEntity);
                }
                break;
            case PUT:
                base = new HttpPut(url);
                if (body != null) {
                    StringEntity httpEntity = new StringEntity(body);
                    ((HttpPut) base).setEntity(httpEntity);
                }
                break;
            case DELETE:
                base = new HttpDelete(url);
                break;
            case GET:
                base = new HttpGet(url);
                break;
            case PATCH:
                base = new HttpPut(url);
                if (body != null) {
                    StringEntity httpEntity = new StringEntity(body);
                    ((HttpPatch) base).setEntity(httpEntity);
                }
                break;
            default:
                break;
        }
        // 设置请求头
        if (base != null && header != null) {
            setHeader(base, header);
        }
        return base;
    }

    private static void setHeader(HttpRequestBase base, Map<String, String> header) {
        // 设置默认请求头
        base.setHeader("Content-Type", "application/json;charset=utf-8");
        base.setHeader("Accept", "application/json");
        if (header.size() > 0) {
            Set<Map.Entry<String, String>> headers = header.entrySet();
            for (Map.Entry<String, String> next : headers) {
                base.setHeader(next.getKey(), next.getValue());
            }
        }
    }

}
