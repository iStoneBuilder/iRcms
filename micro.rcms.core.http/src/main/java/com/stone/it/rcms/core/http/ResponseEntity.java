package com.stone.it.rcms.core.http;

import lombok.Data;

/**
 * @author cj.stone
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
