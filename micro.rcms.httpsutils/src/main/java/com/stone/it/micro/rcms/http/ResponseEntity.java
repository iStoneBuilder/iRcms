package com.stone.it.micro.rcms.http;

import lombok.Data;

/**
 * @author cj.stone
 * @Date 2023/2/21
 * @Desc
 */

@Data
public class ResponseEntity {

    private String code;
    private String message;
    private String body;
    private String errors;

    public ResponseEntity() {
        code = "200";
        message = "success";
    }


}
