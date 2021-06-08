package com.vueblog.Service.Impl;

import com.vueblog.Mapper.CommentMapper;
import com.vueblog.Service.CommentService;
import com.vueblog.VO.CommentVO;
import com.vueblog.pojo.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: haojie
 * @qq :1422471205
 * @CreateTime: 2021-03-12-14-55
 */
@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentMapper commentMapper;
    /**
     * 回复评论
     *
     * @param comment
     * @return
     */
    @Override
    public Boolean relyComment(Comment comment) {
        return commentMapper.relyComment(comment) > 0;
    }

    /**
     * 查看博客评论
     *
     * @param blogId,pageNum,pageSize
     * @return
     */
    @Override
    public List<CommentVO> blogComment(Integer blogId,Integer pageNum,Integer pageSize) {
        return commentMapper.blogComment(blogId, pageNum, pageSize);
    }

    /**
     * 查询当前博客的评论数量
     *
     * @param blogId
     * @return
     */
    @Override
    public Integer countComment(Integer blogId) {
        return commentMapper.countComment(blogId);
    }

    /**
     * 删除博客评论
     *
     * @param CommentId
     * @return
     */
    @Override
    public boolean delComment(Integer CommentId) {
        return commentMapper.delComment(CommentId) > 0;
    }


}
