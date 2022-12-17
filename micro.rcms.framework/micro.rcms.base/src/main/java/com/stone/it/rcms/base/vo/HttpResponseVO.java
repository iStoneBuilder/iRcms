package com.stone.it.rcms.base.vo;

import lombok.Data;

/**
 * @Author iMrJi
 * @Description TODO
 * @Date 2022/9/2 10:20 PM
 * @Version 1.0
 */
@Data
public class HttpResponseVO {

    private String statusCode;
    private String statusMessage;
    private String responseBody;

    public HttpResponseVO() {
        statusCode = "success";
        statusMessage = "success";
    }

}
