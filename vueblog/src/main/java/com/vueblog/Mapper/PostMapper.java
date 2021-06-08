package com.vueblog.Mapper;

import com.vueblog.VO.CollectionVO;
import com.vueblog.VO.postVO;
import com.vueblog.pojo.Collection;
import com.vueblog.pojo.MPost;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author: haojie
 * @qq :1422471205
 * @CreateTime: 2021-03-05-19-48
 */
@Mapper
public interface
PostMapper {
    /**
     * 发表博客
     * @param post
     * @return
     */
    public Integer blogsEdits(MPost post);

    /**
     * 查询置顶的博客
     * @return
     */
    public List<postVO> levelBlogs();

    /**
     * 查看博客详情
     * @param BlogId
     * @return
     */
    public postVO detailBlogs(Integer BlogId);

    /**
     * 删除博客
     * @param id
     * @return
     */
    public Integer delBlogs(Integer id);

    /**
     * 博客置顶
     * @param BlogId
     * @return
     */
    public Integer setLevel(Integer BlogId);

    /**
     * 更新博客
     * @param post
     * @return
     */
    public Integer updateBlogs(MPost post);

    /**
     * 收藏博客
     * @param collectionVO
     * @return
     */
    public Integer collectionBlogs(CollectionVO collectionVO);

    /**
     * 判断博客是否被收藏
     * @param blogId
     * @param userId
     * @return
     */
    public Collection getCollectionAssert(Integer blogId,Integer userId);

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
}
