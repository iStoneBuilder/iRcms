package com.stone.it.micro.rcms.http;

import org.apache.http.client.HttpClient;

import java.util.Map;

/**
 * @author cj.stone
 * @Date 2022/12/14
 * @Desc
 */
public class RequestUtil extends RequestExecute {


	public static ResponseEntity doGet(String url) {
		return doGet(url, null);
	}

	public static ResponseEntity doGet(String url, Map<String, String> params) {
		return doGet(url, params, null);
	}

	public static ResponseEntity doGet(String url, Map<String, String> params, Map<String, String> header) {
		return doGet(getClient(url), url, params, header);
	}

	public static ResponseEntity doGet(HttpClient client, String url, Map<String, String> params,
	                                   Map<String, String> header) {
		return execute(client, RequestType.GET, url, null, header, params);
	}

	public static ResponseEntity doPut(String url, String body) {
		return doPut(url, body, null);
	}

	public static ResponseEntity doPut(String url, String body, Map<String, String> header) {
		return doPut(getClient(url), url, body, header);
	}

	public static ResponseEntity doPut(HttpClient client, String url, String body, Map<String, String> header) {
		return execute(client, RequestType.PUT, url, body, header, null);
	}

	/**
	 * Post请求：后一个请求不会把第一个请求覆盖掉。（所以Post用来增资源）
	 *
	 * @param url
	 * @param body
	 * @return
	 * @
	 */
	public static ResponseEntity doPost(String url, String body) {
		return doPost(url, body, null);
	}

	public static ResponseEntity doPost(String url, String body, Map<String, String> header) {
		return doPost(getClient(url), url, body, header);
	}

	public static ResponseEntity doPost(HttpClient client, String url, String body, Map<String, String> header) {
		return execute(client, RequestType.POST, url, body, header, null);
	}

	/**
	 * @param url
	 * @return
	 * @
	 */
	public static ResponseEntity doDelete(String url) {
		return doDelete(url, null);
	}

	public static ResponseEntity doDelete(String url, String body) {
		return doDelete(url, body, null);
	}

	public static ResponseEntity doDelete(String url, String body, Map<String, String> header) {
		return doDelete(getClient(url), url, body, header);
	}

	public static ResponseEntity doDelete(HttpClient client, String url, String body, Map<String, String> header) {
		return execute(client, RequestType.DELETE, url, body, header, null);
	}

}
