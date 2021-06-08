package com.vueblog.Service;

import com.vueblog.VO.postVO;
import com.vueblog.search.model.postDocument;

import java.util.List;

/**
 * @Author: haojie
 * @qq :1422471205
 * @CreateTime: 2021-03-17-19-42
 */
public interface SearchService {
    /**
     * 初始化es数据
     * @param postVOS
     * @return
     */
   public Integer initEsData(List<postVO> postVOS);

    /**
     * 使用es搜索引擎查询博客
     * @param keyWord
     * @return
     */
    List<postDocument> searchBlogs(String keyWord);

    /**
     * 博客创建更新es数据
     * @param postId
     */
    void createBlog(Integer postId);

    /**
     * 删除博客同步e's
     * @param postId
     */
    void removeBlog(Integer postId);

    /**
     * 更新博客同步es
     * @param postId
     */
    void updateBlog(Integer postId);
}
