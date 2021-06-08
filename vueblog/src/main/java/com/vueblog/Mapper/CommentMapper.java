package com.vueblog.Mapper;

import com.vueblog.VO.CommentVO;
import com.vueblog.pojo.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.bind.annotation.Mapping;

import java.util.List;

/**
 * @Author: haojie
 * @qq :1422471205
 * @CreateTime: 2021-03-12-14-45
 */
@Mapper
public interface CommentMapper {
    /**
     * 回复评论
     * @param comment
     * @return
     */
    public Integer relyComment(Comment comment);

    /**
     * 查看博客评论
     * @param blogId,pageNum,pageSize
     * @return
     */
    public List<CommentVO> blogComment(Integer blogId,Integer pageNum,Integer pageSize);

    /**
     * 查询当前博客的评论数量
     * @param blogId
     * @return
     */
    public Integer countComment(Integer blogId);

    /**
     * 删除博客评论
     * @param CommentId
     * @return
     */
    public Integer delComment(Integer CommentId);
}
