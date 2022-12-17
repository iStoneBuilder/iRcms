package com.stone.it.micro.rcms.http;

import com.stone.it.rcms.base.util.PropertiesUtil;
import com.stone.it.rcms.base.vo.HttpResponseVO;
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
 * @Date 2022/12/14
 * @Desc
 */
public class HttpRequestUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(HttpRequestUtil.class);

    private static final boolean isHttp = "true".equals(PropertiesUtil.getValue("micro.stone.client", "true"));

    /**
     * GET请求
     *
     * @param url
     * @return
     * @throws Exception
     */
    public static HttpResponseVO doGet(String url) throws Exception {
        return doGet(url, null, null);
    }

    /**
     * @param url
     * @param params
     * @return
     * @throws Exception
     */
    public static HttpResponseVO doGet(String url, Map<String, String> params) throws Exception {
        return doGet(url, params, null);
    }

    /**
     * @param url
     * @param params
     * @param headers
     * @return
     * @throws Exception
     */
    public static HttpResponseVO doGet(String url, Map<String, String> params, Map<String, String> headers) throws Exception {
        return execute("GET", url, null, headers, params);
    }

    /**
     * PUT请求：如果两个请求相同，后一个请求会把第一个请求覆盖掉。（所以PUT用来改资源）
     *
     * @param url
     * @param body
     * @return
     * @throws Exception
     */
    public static HttpResponseVO doPut(String url, String body) throws Exception {
        return doPut(url, body, null);
    }

    public static HttpResponseVO doPut(String url, String body, Map<String, String> headers) throws Exception {
        return execute("PUT", url, body, headers, null);
    }

    /**
     * Post请求：后一个请求不会把第一个请求覆盖掉。（所以Post用来增资源）
     *
     * @param url
     * @param body
     * @return
     * @throws Exception
     */
    public static HttpResponseVO doPost(String url, String body) throws Exception {
        return doPost(url, body, null);
    }

    public static HttpResponseVO doPost(String url, String body, Map<String, String> headers) throws Exception {
        return execute("POST", url, body, headers, null);
    }

    /**
     * @param url
     * @return
     * @throws Exception
     */
    public static HttpResponseVO doDelete(String url) throws Exception {
        return doDelete(url, null, null);
    }

    public static HttpResponseVO doDelete(String url, String body) throws Exception {
        return doDelete(url, body, null);
    }

    public static HttpResponseVO doDelete(String url, String body, Map<String, String> headers) throws Exception {
        return execute("DELETE", url, body, headers, null);
    }

    private static HttpResponseVO execute(String methodType, String url, String body, Map<String, String> header, Map<String, String> params) throws Exception {
        LOGGER.info("HTTP REQUEST TYPE IS : " + methodType);
        // 处理请求参数
        url = handleUrl(url, params);
        LOGGER.info("HTTP REQUEST URL IS : " + url);
        // 获取客户端
        HttpClient client = isHttp ? HttpClientUtil.getClient(url) : HttpClientUtil.getHttpsClient(url);
        // 获取Base
        HttpRequestBase requestBase = getRequestBase(methodType, url, body, header);
        // 执行请求
        HttpResponse httpResponse = client.execute(requestBase);
        HttpResponseVO responseVO = new HttpResponseVO();
        LOGGER.info("HTTP RESPONSE STATUS CODE IS : " + httpResponse.getStatusLine().getStatusCode());
        responseVO.setStatusCode(String.valueOf(httpResponse.getStatusLine().getStatusCode()));
        String dataResponse = EntityUtils.toString(httpResponse.getEntity(), Charsets.UTF_8);
        responseVO.setResponseBody(dataResponse);
        LOGGER.info("HTTP RESPONSE DATA IS : " + dataResponse);
        if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            responseVO.setStatusMessage("success");
        } else {
            responseVO.setStatusMessage("fail");
        }
        return responseVO;
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

    protected static HttpRequestBase getRequestBase(String methodType, String url, String body, Map<String, String> header) throws UnsupportedEncodingException {
        HttpRequestBase base = null;
        switch (methodType) {
            case "POST":
                base = new HttpPost(url);
                if (body != null) {
                    StringEntity httpEntity = new StringEntity(body);
                    ((HttpPost) base).setEntity(httpEntity);
                }
                break;
            case "PUT":
                base = new HttpPut(url);
                if (body != null) {
                    StringEntity httpEntity = new StringEntity(body);
                    ((HttpPut) base).setEntity(httpEntity);
                }
                break;
            case "DELETE":
                base = new HttpDelete(url);
                break;
            case "GET":
                base = new HttpGet(url);
                break;
            default:
                break;
        }
        // 设置请求头
        if (header != null) {
            setHeader(base, header);
        }
        return base;
    }

    private static void setHeader(HttpRequestBase base, Map<String, String> header) {
        // 设置默认请求头
        base.setHeader("Content-Type", "application/json;charset=utf-8");
        base.setHeader("Accept", "application/json");
        if (header.size() == 0) {
            return;
        }
        Set<Map.Entry<String, String>> headers = header.entrySet();
        for (Map.Entry<String, String> next : headers) {
            base.setHeader(next.getKey(), next.getValue());
        }
    }

}
