package com.vueblog.VO;

import com.vueblog.pojo.MPost;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @Author: haojie
 * @qq :1422471205
 * @CreateTime: 2021-02-05-19-34
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class postVO extends MPost {
    //作者id
    private Integer authorId;
    //作者名称
    private String authorName;
    //作者头像
    private String authorAvatar;
    //分类名称
    private String categoryName;
}
