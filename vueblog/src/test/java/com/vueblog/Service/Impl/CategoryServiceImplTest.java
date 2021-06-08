package com.vueblog.Service.Impl;

import com.vueblog.Mapper.CategoryMapper;
import com.vueblog.VO.postVO;
import com.vueblog.pojo.Category;
import com.vueblog.pojo.MPost;
import org.apache.ibatis.annotations.Mapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author: haojie
 * @qq :1422471205
 * @CreateTime: 2021-02-01-17-40
 */
class CategoryServiceImplTest {

    @Autowired
    private CategoryMapper categoryMapper;

    @Test
    @RequestMapping("/list")
    @ResponseBody
    List<postVO>  categoryBloges() {
        List<postVO> mPosts = categoryMapper.categoryBloges();
        return mPosts;
    }
}