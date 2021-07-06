package cn.xydata.dto;

import cn.xydata.common.domain.BaseObject;

import java.util.List;

/**
 * @Author: haojie
 * @qq :1422471205
 * @CreateTime: 2021-07-04-09-54
 */
public class PermRequestPathDto extends BaseObject {
    /**
     * 权限编号
     */
    private String permId;

    /**
     * urlId
     */
    private String uriId;

    /**
     * urlId列表
     */
    private String uriIds;


    public PermRequestPathDto() {
    }

    public PermRequestPathDto(String permId, String uriId, String uriIds) {
        this.permId = permId;
        this.uriId = uriId;
        this.uriIds = uriIds;
    }

    public String getPermId() {
        return permId;
    }

    public void setPermId(String permId) {
        this.permId = permId;
    }

    public String getUriId() {
        return uriId;
    }

    public void setUriId(String uriId) {
        this.uriId = uriId;
    }

    public String getUriIds() {
        return uriIds;
    }

    public void setUriIds(String uriIds) {
        this.uriIds = uriIds;
    }

    @Override
    public String toString() {
        return "PermRequestPathDto{" +
                "permId='" + permId + '\'' +
                ", uriId='" + uriId + '\'' +
                ", uriIds='" + uriIds + '\'' +
                '}';
    }
}
