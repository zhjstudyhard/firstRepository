package com.vueblog.Service.Impl;
import com.vueblog.Mapper.CategoryMapper;
import com.vueblog.Service.CategoryService;
import com.vueblog.VO.postVO;
import com.vueblog.pojo.MPost;
import com.vueblog.untils.DateUntils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Author: haojie
 * @qq :1422471205
 * @CreateTime: 2021-02-01-17-06
 */
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private RedisTemplate redisTemplate;
    /**
     * 博客分类
     *
     * @return
     */
    @Override
    public List<postVO> categoryBloges() {
        List<postVO> mPosts = categoryMapper.categoryBloges();
        return mPosts;
    }


    /**
     * 初始化博客评论数
     * 设置redis的zset数据类型，key是博客id,score是评论数
     */
    @Override
    public void initWeek() {
        //存放相同日期博客zset集合
        ArrayList<String> keyDays = new ArrayList<>();
        List<MPost> mPosts = categoryMapper.commentBlogs();
        //格式化时间"yyyy-MM-dd"格式
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        for(MPost post : mPosts){
            Date created = post.getCreated();
            //获取时间2021-12-3类似的格式
            String format = simpleDateFormat.format(created);
            //在redis中用key创建zset数据类型，value为博客id,评论数为score
            String key = "day:rank"+format;
            redisTemplate.opsForZSet().add(key,post.getId(),post.getCommentCount());
            //获取博客发布日期距离当前日期的时间间隔
            long time = DateUntils.dayInterval(format,simpleDateFormat.format(new Date()));
            System.out.println("time: "+time);
            //设置过期时间
            long expired = (7 - time)*24*60*60;
            redisTemplate.expire(key,expired, TimeUnit.SECONDS);
            keyDays.add(key);
            this.hashAndBlogsDate(post,expired);
        }
        redisTemplate.opsForZSet().unionAndStore("day:rank",keyDays,"week:rank");
    }

    /**
     * 动态刷新文章阅读数量
     * @param id
     */
    @Override
    public void setViewCounts(Integer id) {

        //redis的缓存博客基本信息的hash字典名
        String key = "blogsCache"+id;
        //获取redis缓存中的响应博客的阅读量
        Integer viewCounts = (Integer) redisTemplate.opsForHash().get(key, "blog:viewCount");
        redisTemplate.opsForHash().put(key,"blog:viewCount",viewCounts+1);
    }

    /**
     * 批量更新博客阅读量
     *
     * @param list
     * @return
     */
    @Override
    public boolean batchUpdateViewCounts(List<MPost> list) {
        Integer integer = categoryMapper.batchUpdateViewCounts(list);
        System.out.println("更新的博客数量："+integer);
        return integer > 0;
    }

    /**
     * 根据id批量查询博客
     *
     * @param list
     * @return
     */
    @Override
    public List<MPost> selectBatchMpost(List<String> list) {
        return categoryMapper.selectBatchMpost(list);
    }

    /**
     * 缓存博客基本数据
     * @param post
     * @param expired
     */
    private void hashAndBlogsDate(MPost post, long expired) {
         String key = "blogsCache"+post.getId();
         if (!redisTemplate.hasKey(key)){
             //博客id
             redisTemplate.opsForHash().put(key,"blog:ID",post.getId());
             //博客题目
             redisTemplate.opsForHash().put(key,"blog:title",post.getTitle());
             //博客内容
             redisTemplate.opsForHash().put(key,"blog:content",post.getContent());
             //博客评论数量
             redisTemplate.opsForHash().put(key,"blog:commentCount",post.getCommentCount());
             //博客被查看的数量
             redisTemplate.opsForHash().put(key,"blog:viewCount",post.getViewCount());
             //设置redis的缓存过期时间
             redisTemplate.expire(key, expired, TimeUnit.SECONDS);
         }
    }

}
