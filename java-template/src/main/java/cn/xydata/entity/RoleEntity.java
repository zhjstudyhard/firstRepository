package cn.xydata.entity;

import cn.xydata.common.domain.BaseObject;
import org.springframework.beans.BeanUtils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @Author: haojie
 * @qq :1422471205
 * @CreateTime: 2021-06-29-16-04
 */
@Entity
@Table(name = "t_aaa_role")
public class RoleEntity extends BaseObject {
    /**
     * 角色编码
     */
    @Column(name = "rolecode")
    @NotNull(message = "角色代码不能为空")
    private String roleCode;

    /**
     * 角色名称
     */
    @Column(name = "rolename")
    @NotBlank(message = "角色名不能为空")
    private String roleName;

    /**
     * 获取dto或vo
     *
     * @param clazz
     * @param <T>
     * @return
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public <T> T getDto(Class<T> clazz) throws IllegalAccessException, InstantiationException {
        T target = clazz.newInstance();
        BeanUtils.copyProperties(this, target);
        return target;
    }

    public RoleEntity() {
    }

    public RoleEntity(@NotNull(message = "角色代码不能为空") String roleCode, @NotBlank(message = "角色名不能为空") String roleName) {
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
        return "RoleEntity{" +
                "roleCode='" + roleCode + '\'' +
                ", roleName='" + roleName + '\'' +
                '}';
    }
}
