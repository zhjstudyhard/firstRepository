package cn.xydata.dto;

import cn.xydata.common.domain.BaseObject;

import java.util.List;

/**
 * @Author: haojie
 * @qq :1422471205
 * @CreateTime: 2021-07-03-11-16
 */
public class RolePermissionDto extends BaseObject {

    /**
     * 权限编号
     */
    private String permId;

    /**
     * 角色编号
     */
    private String roleId;

    /**
     * 权限id集合
     */
    private List<String> permIds;

    public RolePermissionDto() {
    }

    public RolePermissionDto(String permId, String roleId, List<String> permIds) {
        this.permId = permId;
        this.roleId = roleId;
        this.permIds = permIds;
    }

    public String getPermId() {
        return permId;
    }

    public void setPermId(String permId) {
        this.permId = permId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public List<String> getPermIds() {
        return permIds;
    }

    public void setPermIds(List<String> permIds) {
        this.permIds = permIds;
    }

    @Override
    public String toString() {
        return "RolePermissionDto{" +
                "permId='" + permId + '\'' +
                ", roleId='" + roleId + '\'' +
                ", permIds=" + permIds +
                '}';
    }
}
