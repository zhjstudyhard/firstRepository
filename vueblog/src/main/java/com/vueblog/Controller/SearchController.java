package com.vueblog.Controller;

import com.vueblog.Service.CategoryService;
import com.vueblog.Service.SearchService;
import com.vueblog.VO.postVO;
import com.vueblog.pojo.Result;
import com.vueblog.search.model.postDocument;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Author: haojie
 * @qq :1422471205
 * @CreateTime: 2021-03-17-20-19
 */
@Controller
@RequestMapping("/search")
public class SearchController {

     @Autowired
     private CategoryService categoryService;

     @Autowired
     private SearchService searchService;

    /**
     * 初始化es数据
     * @return
     */
    @PostMapping("/initEsData")
    @ResponseBody
    @RequiresPermissions("serach:update")
    public Result initEsData(){
        List<postVO> postVOS = categoryService.categoryBloges();
        //保存博客数据保存到es中
        Integer total = searchService.initEsData(postVOS);
        return Result.success(total);
    }

    /**
     * 使用es搜索引擎查询博客
     * @param keyWord
     * @return
     */
    @GetMapping("/searchBlogs")
    @ResponseBody
    public Result searchBlogs(String keyWord) {
        List<postDocument> postDocuments = searchService.searchBlogs(keyWord);
        return Result.success(postDocuments);
    }
}
