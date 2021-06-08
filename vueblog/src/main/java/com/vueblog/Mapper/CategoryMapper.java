package com.vueblog.Mapper;

import com.vueblog.VO.postVO;
import com.vueblog.pojo.MPost;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author: haojie
 * @qq :1422471205
 * @CreateTime: 2021-01-25-16-12
 */
@Mapper
public interface CategoryMapper {
    /**
     * 博客分类
     * @return
     */
    List<postVO> categoryBloges();

    /**
     * 查看一周内（7天内）发表的博客
     * @return
     */
    List<MPost> commentBlogs();

    /**
     * 批量更新博客阅读量
     * @param list
     * @return
     */
    Integer batchUpdateViewCounts(List<MPost> list);

    /**
     * 根据id批量查询博客
     * @param list
     * @return
     */
    List<MPost> selectBatchMpost(List<String> list);
}
