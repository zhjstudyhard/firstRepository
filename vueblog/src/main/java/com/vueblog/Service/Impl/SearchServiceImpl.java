package com.vueblog.Service.Impl;

import com.vueblog.Service.PostService;
import com.vueblog.Service.SearchService;
import com.vueblog.VO.postVO;
import com.vueblog.pojo.MPost;
import com.vueblog.pojo.Page;
import com.vueblog.search.model.postDocument;
import com.vueblog.search.repository.ESreposity;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: haojie
 * @qq :1422471205
 * @CreateTime: 2021-03-17-19-43
 */
@Slf4j
@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    private ESreposity eSreposity;

    @Autowired
    private PostService postService;

    /**
     * 初始化es数据
     *
     * @param postVOS
     * @return
     */
    @Override
    public Integer initEsData(List<postVO> postVOS) {
        ModelMapper modelMapper = new ModelMapper();
        if(postVOS == null || postVOS.isEmpty()) {
            return 0;
        }
        //创建postDocument类型数组
        ArrayList<postDocument> list = new ArrayList<>();
        for (postVO postVo : postVOS) {
            //转换model类型，postVo转换成postDocument
            postDocument postdocument = modelMapper.map(postVo, postDocument.class);
            list.add(postdocument);
        }
        System.out.println("postDocument: "+list);
        //存入es
        eSreposity.saveAll(list);
        return list.size();
    }

    /**
     * 使用es搜索引擎查询博客
     *
     * @param keyWord
     * @return
     */
    @Override
    public List<postDocument> searchBlogs(String keyWord) {
        //es条件构造器
        MultiMatchQueryBuilder builders = QueryBuilders.multiMatchQuery(keyWord,"title", "authorName", "categoryName");
        //查询es数据
        ArrayList<postDocument> list = new ArrayList<>();
        Iterable<postDocument> postsPostDocuments = eSreposity.search(builders);
        for (postDocument post : postsPostDocuments){
            list.add(post);
            System.out.println("post: "+post);
        }
        return list;
    }

    /**
     * 博客创建更新es数据
     *
     * @param postId
     */
    @Override
    public void createBlog(Integer postId) {
        //获取当前的创建的信息
        postVO postVO = postService.detailBlogsEdit(postId);
        ModelMapper modelMapper = new ModelMapper();
        //映射类型
        postDocument postDocument = modelMapper.map(postVO, postDocument.class);

        //同步到es中
        eSreposity.save(postDocument);

        log.info("es 索引更新成功！ ---> {}", postDocument.toString());

    }

    /**
     * 删除博客同步e's
     *
     * @param postId
     */
    @Override
    public void removeBlog(Integer postId) {
        System.out.println("id: "+postId);
        eSreposity.deleteById(postId);
    }

    /**
     * 更新博客同步es
     *
     * @param postId
     */
    @Override
    public void updateBlog(Integer postId) {
        //获取当前的创建的信息
        postVO postVO = postService.detailBlogsEdit(postId);
        ModelMapper modelMapper = new ModelMapper();

        //映射类型
        postDocument postDocument = modelMapper.map(postVO, postDocument.class);

        //同步到es中
        eSreposity.save(postDocument);

        log.info("es 索引更新成功！ ---> {}", postDocument.toString());
    }
}
