package cn.xydata.vo;

import cn.xydata.common.domain.BaseObject;

/**
 * @Author: haojie
 * @qq :1422471205
 * @CreateTime: 2021-07-02-16-29
 */
public class UserRoleVo extends BaseObject {
    /**
     * 用户id
     */
    private String userId;

    /**
     * 角色id
     */
    private String roleId;

    public UserRoleVo() {
    }

    public UserRoleVo(String userId, String roleId) {
        this.userId = userId;
        this.roleId = roleId;
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
        return "UserRoleVo{" +
                "userId='" + userId + '\'' +
                ", roleId='" + roleId + '\'' +
                '}';
    }
}
