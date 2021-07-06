package cn.xydata.vo;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: haojie
 * @qq :1422471205
 * @CreateTime: 2021-07-05-10-31
 */
public class LoginUserVo implements Serializable {

    /**
     * description: token
     */
    private String token;

    /**
     * description: 用户名
     */
    private String username;

    /**
     * description: 密码
     */
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

    public LoginUserVo() {
    }

    public LoginUserVo(String token, String username, String password, List<String> roles, List<String> auths, List<String> urls) {
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
        return "LoginUserVo{" +
                "token='" + token + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", roles=" + roles +
                ", auths=" + auths +
                ", urls=" + urls +
                '}';
    }
}
