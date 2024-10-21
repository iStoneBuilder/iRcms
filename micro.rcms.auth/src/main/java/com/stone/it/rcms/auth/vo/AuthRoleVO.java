package com.stone.it.rcms.auth.vo;
/**
 *
 * @author cj.stone
 * @Date 2024/10/18
 * @Desc
 */
public class AuthRoleVO {
    private String roleId;
    private String roleCode;
    private String roleName;
    private String userId;

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
