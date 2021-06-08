package com.vueblog.search.mq;


import com.vueblog.Service.SearchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MqMessageHandler {
    @Autowired
    SearchService searchService;

    /**
     * 发表博客同步es
     * @param message
     */
    @RabbitListener(bindings = @QueueBinding(//创建临时队列
            value = @Queue, exchange = @Exchange(value = "ems", type = "direct"),
            //包含的路由
            key = {PostMqIndexMessage.CREATE,}))
    public void create(PostMqIndexMessage message) {
        log.info("mq 收到一条消息： {}", message.toString());
        searchService.createBlog(message.getPostId());
    }

    /**
     * 删除博客同步es
     * @param message
     */
    @RabbitListener(bindings = @QueueBinding(//创建临时队列
            value = @Queue, exchange = @Exchange(value = "ems", type = "direct"),
            //包含的路由
            key = {PostMqIndexMessage.REMOVE,}))
    public void remove(PostMqIndexMessage message) {
        System.out.println("mq 收到一条消息： {}"+message.toString());
//        log.info();
        searchService.removeBlog(message.getPostId());
    }

    /**
     * 更新博客同步es
     * @param message
     */
    @RabbitListener(bindings = @QueueBinding(//创建临时队列
            value = @Queue, exchange = @Exchange(value = "ems", type = "direct"),
            //包含的路由
            key = {PostMqIndexMessage.UPDATE,}))
    public void update(PostMqIndexMessage message) {
        log.info("mq 收到一条消息： {}", message.toString());
        searchService.updateBlog(message.getPostId());
    }

}
