package cn.xydata.entity;

import cn.xydata.common.domain.BaseObject;
import org.springframework.beans.BeanUtils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @Author: haojie
 * @qq :1422471205
 * @CreateTime: 2021-07-01-14-27
 */

@Entity
@Table(name = "t_aaa_permission")
public class PermissionEntity extends BaseObject {

    /**
     * 权限编码
     */
    @Column(name = "permcode")
    private String permCode;

    /**
     * 权限名称
     */
    @Column(name = "permname")
    private String permName;

    /**
     * 权限类型
     */
    @Column(name = "permtype")
    private String permType;

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

    public PermissionEntity() {
    }

    public PermissionEntity(String permCode, String permName, String permType) {
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
        return "PermissionEntity{" +
                "permCode='" + permCode + '\'' +
                ", permName='" + permName + '\'' +
                ", permType='" + permType + '\'' +
                '}';
    }
}
