package cn.xydata.entity;

import cn.xydata.common.domain.BaseObject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @Author: haojie
 * @qq :1422471205
 * @CreateTime: 2021-07-04-13-29
 */
@Entity
@Table(name = "t_aaa_requestpath")
public class RequestPathEntity extends BaseObject {

    /**
     * 请求描述
     */
    @Column(name = "description")
    private String description;

    /**
     请求地址
     */
    @Column(name = "url")
    private String url;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "RequestPathEntity{" +
                "description='" + description + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
