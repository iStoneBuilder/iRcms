package com.stone.it.rcms.auth.vo;

import com.stone.it.rcms.core.util.UUIDUtil;

/**
 *
 * @author cj.stone
 * @Date 2024/10/18
 * @Desc
 */
public class PermissionVO {

    /**
     * 权限ID
     */
    private String permissionId;
    /**
     * 权限编码
     */
    private String permissionCode;

    private String apiName;
    private String apiPath;
    private String apiType;
    private String requestType;

    private String description;

    public PermissionVO() {
        this.permissionId = UUIDUtil.getUuid();
    }

    public String getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(String permissionId) {
        this.permissionId = permissionId;
    }

    public String getPermissionCode() {
        return permissionCode;
    }

    public void setPermissionCode(String permissionCode) {
        this.permissionCode = permissionCode;
    }

    public String getApiName() {
        return apiName;
    }

    public void setApiName(String apiName) {
        this.apiName = apiName;
    }

    public String getApiPath() {
        return apiPath;
    }

    public void setApiPath(String apiPath) {
        this.apiPath = apiPath;
    }

    public String getApiType() {
        return apiType;
    }

    public void setApiType(String apiType) {
        this.apiType = apiType;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
