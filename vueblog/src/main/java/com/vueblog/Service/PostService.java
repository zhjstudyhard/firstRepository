package com.vueblog.Service;

import com.vueblog.VO.CollectionVO;
import com.vueblog.VO.postVO;
import com.vueblog.pojo.Collection;
import com.vueblog.pojo.MPost;

import java.util.List;

/**
 * @Author: haojie
 * @qq :1422471205
 * @CreateTime: 2021-03-05-20-31
 */
public interface PostService {
    /**
     * 发表博客
     * @param post
     * @return
     */
    public Boolean blogsEdit(MPost post);

    /**
     * 查看置顶的博客
     * @return
     */
    public List<postVO> levelBlogs();

    /**
     * 查看博客详情
     * @param blogId
     * @return
     */
    public postVO detailBlogsEdit(Integer blogId);

    /**
     * 删除博客
     * @param id
     * @return
     */
    public boolean deleteBlogs(Integer id);

    /**
     * 博客置顶
     * @param BlogId
     * @return
     */
    public boolean setLevel(Integer BlogId);

    /**
     * 更新博客
     * @param post
     * @return
     */
    public Boolean updateBlogs(MPost post);

    /**
     * 收藏博客
     * @param collectionVO
     * @return
     */
    public Boolean collectionBlogs(CollectionVO collectionVO);

    /**
     * 判断博客是否被收藏
     * @param blogId
     * @param userId
     * @return
     */
    public Collection getCollectionAssert(Integer blogId,Integer userId);

    /**
     * 评论数加一
     * @param blogId
     */
    public void AutomaticOne(Long blogId);

    /**
     * 查询博客信息
     * @param blogId
     * @return
     */
    public MPost selectOne(Integer blogId);

    /**
     * 更新博客的评论数量
     * @param post
     * @return
     */
    public Integer updateCommentCount(MPost post);

    /**
     * 查询博客信息
     * @param blogId
     * @return
     */
//    public postVO selectPostVo(Integer blogId);
}
