package com.stone.it.micro.rcms.http;

import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.HttpClientBuilder;

/**
 * @author cj.stone
 * @Date 2022/12/14
 * @Desc
 */
public class HttpClientUtil {

    private static HttpClient httpClient;

    private static HttpClient httpsClient;

    /**
     * 设置连接超时时间，单位毫秒。
     */
    private static final int CONNECT_TIMEOUT = 6000;

    /**
     * 设置连接超时时间，单位毫秒。
     */
    private static final int REQUEST_TIMEOUT = 6000;

    /**
     * 请求获取数据的超时时间(即响应时间)，单位毫秒。
     */
    private static final int SOCKET_TIMEOUT = 6000;

    public static synchronized HttpClient getClient(String uri) {
        if (httpClient == null) {
            RequestConfig config = RequestConfig.custom().setConnectTimeout(CONNECT_TIMEOUT).setConnectionRequestTimeout(REQUEST_TIMEOUT).setSocketTimeout(SOCKET_TIMEOUT).build();
            httpClient = HttpClientBuilder.create().setMaxConnTotal(200).setMaxConnPerRoute(100).setUserAgent("").setDefaultRequestConfig(config).disableAutomaticRetries().build();
        }
        return httpClient;
    }

    public static synchronized HttpClient getHttpsClient(String uri) {
        if (httpsClient == null) {
            RequestConfig config = RequestConfig.custom().setConnectTimeout(CONNECT_TIMEOUT).setConnectionRequestTimeout(REQUEST_TIMEOUT).setSocketTimeout(SOCKET_TIMEOUT).build();
            httpsClient = HttpClientBuilder.create().setMaxConnTotal(200).setMaxConnPerRoute(100).setUserAgent("").setDefaultRequestConfig(config).disableAutomaticRetries().build();
        }
        return httpsClient;
    }

}
