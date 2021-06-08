package com.vueblog.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Author: haojie
 * @qq :1422471205
 * @CreateTime: 2021-03-11-18-54
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CollectionVO {
    private Integer id;
    //当前用户id
    private Integer userId;
    //收藏博客id
    private Integer postId;
    //收藏博客的用户id
    private Integer postUserId;
    private Date created;
    private Date modified;
}
