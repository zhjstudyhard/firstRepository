package com.vueblog.config;

import com.vueblog.Service.CategoryService;
import com.vueblog.pojo.MPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @Author: haojie
 * @qq :1422471205
 * @CreateTime: 2021-02-19-17-21
 */
@Configuration      //1.主要用于标记配置类，兼备Component的效果。
public class blogsViewCountsTask {
    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private CategoryService categoryService;
    //3.添加定时任务
    @Scheduled(cron = "0 0 0 * * ?") //每天凌晨0点触发
    //或直接指定时间间隔，例如：5秒
    //@Scheduled(fixedRate=5000)
    private void configureTasks() {
        //模糊查询hash表中博客缓存的字典
        Set<String> keys = redisTemplate.keys("blogsCache*");
        ArrayList<String> list = new ArrayList<>();
        for (String key : keys) {
            if (redisTemplate.opsForHash().hasKey(key,"blog:viewCount")){
                list.add(key.substring("blogsCache".length()));
            }
        }
        List<MPost> mPosts = categoryService.selectBatchMpost(list);
        for (MPost post : mPosts) {
            Integer s = (Integer) redisTemplate.opsForHash().get("blogsCache" + post.getId(), "blog:viewCount");
            post.setViewCount(s);

        }
        categoryService.batchUpdateViewCounts(mPosts);
    }
}
