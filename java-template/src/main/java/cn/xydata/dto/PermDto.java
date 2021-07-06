package cn.xydata.dto;

import cn.xydata.common.domain.BaseObject;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotBlank;

/**
 * @Author: haojie
 * @qq :1422471205
 * @CreateTime: 2021-07-03-14-04
 */
public class PermDto extends BaseObject {
    /**
     * 权限编号
     */
    @NotBlank(message = "权限编码不能为空")
    private String permCode;

    /**
     * 权限名称
     */
    @NotBlank(message = "权限名称不能为空")
    private String permName;

    /**
     * 权限类型
     */
    @NotBlank(message = "权限类型不能为空")
    private String permType;

    /**
     * 获取Entity对象
     * @param clazz
     * @param <T>
     * @return
     * @throws Exception
     */
    public <T> T getEntity(Class<T> clazz) throws Exception{
        T Entity = clazz.newInstance();
        BeanUtils.copyProperties(this, Entity);
        return Entity;
    }

    public PermDto() {
    }

    public PermDto(String permCode, String permName, String permType) {
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
        return "PermDto{" +
                "permCode='" + permCode + '\'' +
                ", permName='" + permName + '\'' +
                ", permType='" + permType + '\'' +
                '}';
    }
}
