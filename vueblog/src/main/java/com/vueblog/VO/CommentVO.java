package com.vueblog.VO;

import com.vueblog.pojo.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: haojie
 * @qq :1422471205
 * @CreateTime: 2021-03-12-19-44
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentVO extends Comment {
    private Integer CommentId;
    private String authorAvatar;
    private String username;
    private String parentUsername;
}
