package com.vueblog.Service;

import com.vueblog.VO.postVO;
import com.vueblog.pojo.MPost;

import java.util.List;

/**
 * @Author: haojie
 * @qq :1422471205
 * @CreateTime: 2021-02-01-17-06
 */
public interface CategoryService {
    /**
     * 博客分类
     * @return
     */
    List<postVO> categoryBloges();

    /**
     * 初始化博客评论数
     * 设置redis的zset数据类型，key是博客id,score是评论数
     */
    void initWeek();

    /**
     * 动态刷新文章阅读数量
     * @param id
     */
    void setViewCounts(Integer id);

    /**
     * 批量更新博客阅读量
     * @param list
     * @return
     */
    boolean batchUpdateViewCounts(List<MPost> list);

    /**
     * 根据id批量查询博客
     * @param list
     * @return
     */
    List<MPost> selectBatchMpost(List<String> list);
}
