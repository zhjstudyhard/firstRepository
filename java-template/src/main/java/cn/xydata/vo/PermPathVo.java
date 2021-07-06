package cn.xydata.vo;

import cn.xydata.common.domain.BaseObject;

/**
 * @Author: haojie
 * @qq :1422471205
 * @CreateTime: 2021-07-04-10-09
 */
public class PermPathVo extends BaseObject {
    /**
     * 权限编号
     */
    private String permId;

    /**
     * urlId
     */
    private String requestPathId;

    public PermPathVo() {
    }

    public PermPathVo(String permId, String requestPathId) {
        this.permId = permId;
        this.requestPathId = requestPathId;
    }

    public String getPermId() {
        return permId;
    }

    public void setPermId(String permId) {
        this.permId = permId;
    }

    public String getRequestPathId() {
        return requestPathId;
    }

    public void setRequestPathId(String requestPathId) {
        this.requestPathId = requestPathId;
    }

    @Override
    public String toString() {
        return "PermPathVo{" +
                "permId='" + permId + '\'' +
                ", requestPathId='" + requestPathId + '\'' +
                '}';
    }
}
