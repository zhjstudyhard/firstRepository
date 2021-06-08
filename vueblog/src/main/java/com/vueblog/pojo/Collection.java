package com.vueblog.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Author: haojie
 * @qq :1422471205
 * @CreateTime: 2021-03-12-20-55
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Collection {
    private Integer id;
    /**
     * 收藏用户的id
     */
    private Integer userId;
    /**
     * 博客id
     */
    private Integer post_id;
    /**
     * 被收藏博客的作者
     */
    private Integer post_user_id;

    private Date created;

    private Date modified;
}
