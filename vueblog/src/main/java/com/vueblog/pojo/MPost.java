package com.vueblog.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Author: haojie
 * @qq :1422471205
 * @CreateTime: 2021-02-01-16-58
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MPost {
    private static final long serialVersionUID = 1L;
    //主键
    private Integer id;
    //标题
    private String title;
    //内容
    private String content;
    //类目id
    private Integer categoryID;
    //用户id
    private Integer userID;
    //创建时间
    private Date created;
    //修改时间
    private Date modified;

    /**
     * 支持人数
     */
    private Integer voteUp;

    /**
     * 反对人数
     */
    private Integer voteDown;

    /**
     * 访问量
     */
    private Integer viewCount;

    /**
     * 评论数量
     */
    private Integer commentCount;

    /**
     * 是否为精华
     */
    private Boolean recommend;

    /**
     * 置顶等级
     */
    private Integer level;

}
