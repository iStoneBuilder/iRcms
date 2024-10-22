package com.stone.it.rcms.auth.vo;

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
    /**
     * 权限名称
     */
    private String permissionName;
    private String permissionPath;
    /**
     * 权限描述
     */
    private String permissionDesc;
    /**
     * 角色编码
     */
    private String roleCode;

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

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public String getPermissionDesc() {
        return permissionDesc;
    }

    public void setPermissionDesc(String permissionDesc) {
        this.permissionDesc = permissionDesc;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getPermissionPath() {
        return permissionPath;
    }

    public void setPermissionPath(String permissionPath) {
        this.permissionPath = permissionPath;
    }
}
