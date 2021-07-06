package cn.xydata.vo;

import cn.xydata.common.domain.BaseObject;

/**
 * @Author: haojie
 * @qq :1422471205
 * @CreateTime: 2021-07-04-13-52
 */
public class RequestPathVo extends BaseObject {


    /**
     请求地址
     */
    private String url;

    /**
     * 描述
     */
    private String description;

    public RequestPathVo() {
    }

    public RequestPathVo(String url, String description) {
        this.url = url;
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "RequestPathVo{" +
                "url='" + url + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
