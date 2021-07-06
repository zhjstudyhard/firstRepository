package cn.xydata.entity;

import cn.xydata.common.domain.BaseObject;
import org.springframework.beans.BeanUtils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @Author: haojie
 * @qq :1422471205
 * @CreateTime: 2021-07-04-10-17
 */
@Entity
@Table(name = "t_aaa_permrequestpath")
public class PermRequestPathEntity extends BaseObject {

    /**
     * 权限编号
     */
    @Column(name = "permid")
    private String permId;

    /**
     * urlId
     */
    @Column(name = "requestpathid")
    private String requestPathId;

    /**
     * 获取dto或vo
     *
     * @param clazz
     * @param <T>
     * @return
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public <T> T getDto(Class<T> clazz) throws IllegalAccessException, InstantiationException {
        T target = clazz.newInstance();
        BeanUtils.copyProperties(this, target);
        return target;
    }

    public PermRequestPathEntity() {
    }

    public PermRequestPathEntity(String permId, String requestPathId) {
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
        return "PermRequestPathEntity{" +
                "permId='" + permId + '\'' +
                ", requestPathId='" + requestPathId + '\'' +
                '}';
    }
}

