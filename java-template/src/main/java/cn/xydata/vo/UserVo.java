package cn.xydata.vo;

import cn.xydata.common.domain.BaseObject;

import java.util.Date;

/**
 * @Author: haojie
 * @qq :1422471205
 * @CreateTime: 2021-06-30-09-56
 */
public class UserVo extends BaseObject {

    /**
     * 用户是否锁定
     */
    private Integer locked;

    /**
     * 用户名称
     */
    private String username;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 用户是否启用
     */
    private Integer enabled;

    /**
     * 用户是否过期
     */
    private Integer isExpired;

    /**
     * 用户过期时间
     */
    private Date expired;

    public UserVo() {
    }

    public UserVo(Integer locked, String username, String password, Integer enabled, Integer isExpired, Date expired) {
        this.locked = locked;
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.isExpired = isExpired;
        this.expired = expired;
    }

    public Integer getLocked() {
        return locked;
    }

    public void setLocked(Integer locked) {
        this.locked = locked;
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

    public Date getExpired() {
        return expired;
    }

    public void setExpired(Date expired) {
        this.expired = expired;
    }

    @Override
    public String toString() {
        return "UserVo{" +
                "locked=" + locked +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", enabled=" + enabled +
                ", isExpired=" + isExpired +
                ", expired=" + expired +
                '}';
    }
}

