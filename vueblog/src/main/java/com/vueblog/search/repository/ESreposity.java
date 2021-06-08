package com.vueblog.search.repository;

import com.vueblog.VO.userVO;
import com.vueblog.search.model.postDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author: haojie
 * @qq :1422471205
 * @CreateTime: 2021-03-17-10-16
 */
@Repository
public interface ESreposity extends ElasticsearchRepository<postDocument, Integer> {
}
