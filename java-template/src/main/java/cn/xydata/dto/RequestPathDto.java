package cn.xydata.dto;

import org.springframework.beans.BeanUtils;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;

/**
 * @Author: haojie
 * @qq :1422471205
 * @CreateTime: 2021-07-04-14-17
 */
public class RequestPathDto {
    /**
     * id
     */
    private String id;

    /**
     * 描述
     */
    @NotBlank(message = "URL描述不能为空")
    private String description;

    /**
     * url
     */
    @NotBlank(message = "URL不能为空")
    private String url;

    /**
     * 获取实体类
     * @param clazz
     * @param <T>
     * @return
     * @throws Exception
     */
    public <T> T getEntity(Class<T> clazz) throws Exception{
        T target = clazz.newInstance();
        BeanUtils.copyProperties(this, target);
        return target;
    }

    public RequestPathDto() {
    }

    public RequestPathDto(String id, String description, String url) {
        this.id = id;
        this.description = description;
        this.url = url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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
        return "RequestPathDto{" +
                "id='" + id + '\'' +
                ", description='" + description + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
