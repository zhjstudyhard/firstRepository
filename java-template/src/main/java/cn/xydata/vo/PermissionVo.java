package cn.xydata.vo;

import cn.xydata.common.domain.BaseObject;

/**
 * @Author: haojie
 * @qq :1422471205
 * @CreateTime: 2021-07-03-13-42
 */
public class PermissionVo extends BaseObject {
    /**
     * 权限编号
     */
    private String permCode;

    /**
     * 权限名称
     */
    private String permName;

    /**
     * 权限类型
     */
    private String permType;

    public PermissionVo() {
    }

    public PermissionVo(String permCode, String permName, String permType) {
        this.permCode = permCode;
        this.permName = permName;
        this.permType = permType;
    }

    public String getPermCode() {
        return permCode;
    }

    public void setPermCode(String permCode) {
        this.permCode = permCode;
    }

    public String getPermName() {
        return permName;
    }

    public void setPermName(String permName) {
        this.permName = permName;
    }

    public String getPermType() {
        return permType;
    }

    public void setPermType(String permType) {
        this.permType = permType;
    }

    @Override
    public String toString() {
        return "PerssionVo{" +
                "permCode='" + permCode + '\'' +
                ", permName='" + permName + '\'' +
                ", permType='" + permType + '\'' +
                '}';
    }
}
