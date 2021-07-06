package cn.xydata.entity;

import cn.xydata.common.domain.BaseObject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Author: haojie
 * @qq :1422471205
 * @CreateTime: 2021-07-01-13-31
 */
@Entity
@Table(name = "t_aaa_rolepermission")
public class RolePermissionEntity extends BaseObject {

    /**
     * 权限id
     */
    @Column(name = "permid")
    private String permId;

    /**
     * 角色id
     */
    @Column(name = "roleid")
    private String roleId;

    public RolePermissionEntity() {
    }

    public RolePermissionEntity(String permId, String roleId) {
        this.permId = permId;
        this.roleId = roleId;
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

    @Override
    public String toString() {
        return "RolePermissionEntity{" +
                "permId='" + permId + '\'' +
                ", roleId='" + roleId + '\'' +
                '}';
    }
}
