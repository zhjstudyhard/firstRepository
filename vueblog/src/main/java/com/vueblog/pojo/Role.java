package com.vueblog.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author: haojie
 * @qq :1422471205
 * @CreateTime: 2021-03-10-20-16
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role {
    //角色id
    private Integer id;
    //角色名称
    private String roleName;
    //权限集合
    private List<Permission> permissions;
}
