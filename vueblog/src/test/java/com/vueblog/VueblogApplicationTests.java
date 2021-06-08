package com.vueblog;

import com.alibaba.fastjson.JSON;
import com.vueblog.Mapper.PostMapper;
import com.vueblog.Mapper.UserMapper;
import com.vueblog.VO.userVO;
import com.vueblog.search.repository.ESreposity;
import com.vueblog.untils.IDuntils;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class VueblogApplicationTests {
//    @Autowired
//    private UserMapper userMapper;
//    @Autowired
//    private PostMapper postMapper;

    @Autowired
    @Qualifier("restHighLevelClient")
    private RestHighLevelClient client;

    @Autowired
    private ESreposity esreposity;

    @Test
    void myBlogs() throws IOException {
        userVO user = new userVO(1, "赵浩杰", null);
//创建请求
        IndexRequest request = new IndexRequest("post");
// 规则 put joker_index/_doc/1
        request.id("1");
        request.timeout(TimeValue.timeValueSeconds(1));
        request.timeout("1s");
//将我们的数据放入请求 json
        request.source(JSON.toJSONString(user), XContentType.JSON);
//客户端发送请求
        IndexResponse indexRespons = client.index(request, RequestOptions.DEFAULT);
        System.out.println(indexRespons.toString());
        System.out.println(indexRespons.status());
//          esreposity.save();
    }

    @Test
    void test2(){
        System.out.println("uuid: "+ IDuntils.getUUID());
    }
}
