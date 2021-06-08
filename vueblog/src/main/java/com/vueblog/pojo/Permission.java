package com.vueblog.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: haojie
 * @qq :1422471205
 * @CreateTime: 2021-03-18-08-39
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Permission {
    //权限id
    private Integer id;
    //权限标志名称
    private String sn;
}
