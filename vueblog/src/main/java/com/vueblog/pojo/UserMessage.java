package com.vueblog.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author 公众号：java思维导图
 * @since 2019-11-17
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserMessage{

    private static final long serialVersionUID = 1L;
    private Integer id;

    /**
     * 发送消息的用户ID
     */
    private Integer fromUserId;

    /**
     * 接收消息的用户ID
     */
    private Integer toUserId;

    /**
     * 消息可能关联的帖子
     */
    private Integer postId;

    /**
     * 消息可能关联的评论
     */
    private Integer commentId;

    private String content;

    /**
     * 消息类型
     * 0系统消息 1评论文章 2评论评论
     */
    private Integer type;

    /**
     * 消息确认状态，0未查看,1已查看
     */
    private Integer status;

    private Date created;

    private Date modified;

}
