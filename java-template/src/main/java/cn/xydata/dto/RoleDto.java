package cn.xydata.dto;

import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotBlank;

/**
 * @Author: haojie
 * @qq :1422471205
 * @CreateTime: 2021-07-01-09-02
 */

public class RoleDto{
    /**
     * description: id
     */
    private String id;

    /**
     * description: 角色代码
     */
    @NotBlank(message = "角色代码不能为空")
    private String roleCode;

    /**
     * description: 角色名字
     */
    @NotBlank(message = "角色名不能为空")
    private String roleName;


    public String getId() {
        return id;
    }

    public <T> T getEntity(Class<T> clazz) throws Exception{
        T target = clazz.newInstance();
        BeanUtils.copyProperties(this, target);
        return target;
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


