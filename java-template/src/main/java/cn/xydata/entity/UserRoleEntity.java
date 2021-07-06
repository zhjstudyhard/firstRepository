package cn.xydata.entity;

import org.springframework.beans.BeanUtils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * @Author: haojie
 * @qq :1422471205
 * @CreateTime: 2021-06-30-15-28
 */

@Entity
@Table(name = "t_aaa_userrole")
public class UserRoleEntity extends BaseEntity{
    @Id
    private String id;

    /**
     * 用户id
     */
    @Column(name = "userid")
    @NotNull
    private String userId;

    /**
     * 角色id
     */
    @Column(name = "roleid")
    @NotNull
    private String roleId;

    /**
     * 获取VO
     * @param clazz
     * @param <T>
     * @return
     * @throws Exception
     */
    public <T> T getDto(Class<T> clazz) throws Exception {
        T dto = clazz.newInstance();
        BeanUtils.copyProperties(this, dto);
        return dto;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return "UserRoleEntity{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", roleId='" + roleId + '\'' +
                '}';
    }
}
