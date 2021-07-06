package cn.xydata.entity;

import cn.xydata.common.domain.BaseObject;
import org.springframework.beans.BeanUtils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @Author: haojie
 * @qq :1422471205
 * @CreateTime: 2021-06-29-09-00
 */
@Entity
@Table(name = "t_aaa_user")
public class UserEntity extends BaseObject {
    /**
     * 用户过期时间
     */
    @Column(name = "expired")
    private Date expired;

    /**
     * 用户是否锁定
     */
    @Column(name = "locked")
    private Integer locked;

    /**
     * 用户名称
     */
    @Column(name = "username")
    @NotNull
    private String username;

    /**
     * 用户密码
     */
    @Column(name = "password")
    private String password;

    /**
     * 用户是否启用
     */
    @Column(name = "enabled")
    private Integer enabled;

    /**
     * 用户是否过期
     */
    @Column(name = "isexpired")
    private Integer isExpired;


    /**
     * 获取dto对象
     *
     * @param clazz
     * @return
     * @throws Exception
     */
    public <T> T getDto(Class<T> clazz) throws Exception {
        T dto = clazz.newInstance();
        BeanUtils.copyProperties(this, dto);
        return dto;
    }


    public UserEntity() {
    }

    public UserEntity(Date expired, @NotNull String username, @NotBlank String password, Integer enabled, Integer isExpired, Integer locked) {
        this.expired = expired;
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.isExpired = isExpired;
        this.locked = locked;
    }

    public Date getExpired() {
        return expired;
    }

    public void setExpired(Date expired) {
        this.expired = expired;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getEnabled() {
        return enabled;
    }

    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
    }

    public Integer getIsExpired() {
        return isExpired;
    }

    public void setIsExpired(Integer isExpired) {
        this.isExpired = isExpired;
    }

    public Integer getLocked() {
        return locked;
    }

    public void setLocked(Integer locked) {
        this.locked = locked;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "expired=" + expired +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", enabled=" + enabled +
                ", isExpired=" + isExpired +
                ", locked=" + locked +
                '}';
    }
}
