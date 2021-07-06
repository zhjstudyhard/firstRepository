package cn.xydata.dto;

import cn.xydata.common.domain.BaseObject;
import org.hibernate.sql.Update;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
/**
 * @Author: haojie
 * @qq :1422471205
 * 用户表dto
 * @CreateTime: 2021-06-29-09-00
 */
public class UserDto extends BaseObject {

    public interface Insert{

    }

    public interface Update {

    }

    /**
     * 用户是否锁定
     */
    @NotNull(message = "锁定值不能为空")
    private Integer locked;

    /**
     * 用户名称
     */
    @NotBlank(message = "用户名不能为空")
    private String username;

    /**
     * 用户密码
     */
    @NotBlank(message = "密码不能为空")
    private String password;

    /**
     * 用户是否启用
     */
    @NotNull(message = "启用值不能为空")
    private Integer enabled;

    /**
     * 用户是否过期
     */
    private Integer isExpired;

    /**
     * 用户过期时间
     */
    private Date expired;


    /**
     * 获取Entity对象
     * @param clazz
     * @param <T>
     * @return
     * @throws Exception
     */
    public <T> T getDto(Class<T> clazz) throws Exception{
        T dto = clazz.newInstance();
        BeanUtils.copyProperties(this, dto);
        return dto;
    }

    public UserDto() {
    }
    public UserDto(Integer locked, String username, String password, Integer enabled, Integer isExpired, Date expired) {
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
        return "UserDto{" +
                "locked=" + locked +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", enabled=" + enabled +
                ", isExpired=" + isExpired +
                ", expired=" + expired +
                '}';
    }
}