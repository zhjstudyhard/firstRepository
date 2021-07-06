package cn.xydata.dto;

import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;

/**
 * @Author: haojie
 * @qq :1422471205
 * @CreateTime: 2021-07-05-10-05
 */
public class UserLoginDto implements Serializable {

    /**
     * description: token
     */
    private String token;

    /**
     * description: 用户名
     */
    @NotBlank(message = "用户名不能为空")
    private String username;

    /**
     * description: 密码
     */
    @NotBlank(message = "密码不能为空")
    private String password;

    /**
     * description: 角色列表
     */
    private List<String> roles;

    /**
     * description: 权限列表
     */
    private List<String> auths;

    /**
     * description: 地址列表
     */
    private List<String> urls;

    /**
     * 获取VO或实体类
     * @param clazz
     * @param <T>
     * @return
     * @throws Exception
     */
    public <T> T getVo(Class<T> clazz) throws Exception{
        T Vo = clazz.newInstance();
        BeanUtils.copyProperties(this, Vo);
        return Vo;
    }

    public UserLoginDto() {
    }

    public UserLoginDto(String token, @NotBlank(message = "用户名不能为空") String username, @NotBlank(message = "密码不能为空") String password, List<String> roles, List<String> auths, List<String> urls) {
        this.token = token;
        this.username = username;
        this.password = password;
        this.roles = roles;
        this.auths = auths;
        this.urls = urls;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
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

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public List<String> getAuths() {
        return auths;
    }

    public void setAuths(List<String> auths) {
        this.auths = auths;
    }

    public List<String> getUrls() {
        return urls;
    }

    public void setUrls(List<String> urls) {
        this.urls = urls;
    }

    @Override
    public String toString() {
        return "UserLoginDto{" +
                "token='" + token + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", roles=" + roles +
                ", auths=" + auths +
                ", urls=" + urls +
                '}';
    }
}
