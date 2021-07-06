package cn.xydata.dto;

import cn.xydata.common.domain.BaseObject;

/**
 * @Author: haojie
 * @qq :1422471205
 * @CreateTime: 2021-07-01-11-29
 */

public class DelRoleDto extends BaseObject {

    /**
     * description: 角色代码
     */
    private String roleCode;

    /**
     * description: 角色名字
     */
    private String roleName;

    public DelRoleDto() {
    }

    public DelRoleDto(String roleCode, String roleName) {
        this.roleCode = roleCode;
        this.roleName = roleName;
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

    @Override
    public String toString() {
        return "DelRoleDto{" +
                "roleCode='" + roleCode + '\'' +
                ", roleName='" + roleName + '\'' +
                '}';
    }
}
