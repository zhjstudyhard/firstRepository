package com.vueblog.Service.Impl;

import com.vueblog.Mapper.PostMapper;
import com.vueblog.Service.PostService;
import com.vueblog.VO.CollectionVO;
import com.vueblog.VO.postVO;
import com.vueblog.pojo.Collection;
import com.vueblog.pojo.MPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: haojie
 * @qq :1422471205
 * @CreateTime: 2021-03-05-20-33
 */
@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostMapper postMapper;

    /**
     * 发表博客
     *
     * @param post
     * @return
     */
    @Override
    public Boolean blogsEdit(MPost post) {
        return postMapper.blogsEdits(post) > 0;
    }

    /**
     * 查看置顶的博客
     *
     * @return
     */
    @Override
    public List<postVO> levelBlogs() {
        return postMapper.levelBlogs();
    }

    /**
     * 查看博客详情
     *
     * @param blogId
     * @return
     */
    @Override
    public postVO detailBlogsEdit(Integer blogId) {
        return postMapper.detailBlogs(blogId);
    }

    /**
     * 删除博客
     *
     * @param id
     * @return
     */
    @Override
    public boolean deleteBlogs(Integer id) {
        return postMapper.delBlogs(id) > 0;
    }

    /**
     * 博客置顶
     *
     * @param BlogId
     * @return
     */
    @Override
    public boolean setLevel(Integer BlogId) {
        return postMapper.setLevel(BlogId) > 0;
    }

    /**
     * 更新博客
     *
     * @param post
     * @return
     */
    @Override
    public Boolean updateBlogs(MPost post) {
        return postMapper.updateBlogs(post) > 0;
    }

    /**
     * 收藏博客
     *
     * @param collectionVO
     * @return
     */
    @Override
    public Boolean collectionBlogs(CollectionVO collectionVO) {
        return postMapper.collectionBlogs(collectionVO) > 0;
    }

    /**
     * 判断博客是否被收藏
     *
     * @param blogId
     * @param userId
     * @return
     */
    @Override
    public Collection getCollectionAssert(Integer blogId, Integer userId) {
        return postMapper.getCollectionAssert(blogId, userId);
    }

    /**
     * 查询博客信息
     *
     * @param blogId
     * @return
     */
    @Override
    public MPost selectOne(Integer blogId) {
        return postMapper.selectOne(blogId);
    }

    /**
     * 更新博客的评论数量
     *
     * @param post
     * @return
     */
    @Override
    public Integer updateCommentCount(MPost post) {
        return postMapper.updateCommentCount(post);
    }

    /**
     * 评论数加一
     *
     * @param blogId
     */
    @Override
    public void AutomaticOne(Long blogId) {
        MPost post = selectOne(blogId.intValue());
        //获取博客对象并且评论数加一
        post.setCommentCount(post.getCommentCount() + 1);
        System.out.println("查询出post: "+post);
        //更新博客
        updateCommentCount(post);
    }


}
