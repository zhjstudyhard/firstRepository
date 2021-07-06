package cn.xydata.vo;

import cn.xydata.entity.BaseEntity;

/**
 * @Author: haojie
 * @qq :1422471205
 * @CreateTime: 2021-06-30-15-26
 */
public class RoleVo extends BaseEntity {
    /**
     * description: id
     */
    private String id;

    /**
     * description: 角色代码
     */
    private String roleCode;

    /**
     * description: 角色名字
     */
    private String roleName;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
        return "RoleVO{" +
                "id='" + id + '\'' +
                ", roleCode='" + roleCode + '\'' +
                ", roleName='" + roleName + '\'' +
                '}';
    }
}

