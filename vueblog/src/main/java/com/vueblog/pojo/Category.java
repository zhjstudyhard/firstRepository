package com.vueblog.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: haojie
 * @qq :1422471205
 * @CreateTime: 2021-01-25-16-14
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    private Integer id;
    private String name;
    private String context;
    private String summary;
    private String icon;
    private Integer postCount;
    private Integer orderNum;
    private Integer parentId;
    private String meta_keywords;
    private String meta_description;
}
