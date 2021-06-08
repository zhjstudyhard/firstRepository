package com.vueblog.Controller;
import com.vueblog.Service.CategoryService;
import com.vueblog.VO.postVO;
import com.vueblog.pojo.Category;
import com.vueblog.pojo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.*;


/**
 * @Author: haojie
 * @qq :1422471205
 * @CreateTime: 2021-02-01-17-39
 */
@Controller
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 查询博客
     *
     * @return
     */
    @GetMapping("/findAll")
    @ResponseBody
    public Result find() {
        List<postVO> mPosts = categoryService.categoryBloges();
        if (mPosts != null) {
            return Result.success(mPosts);
        }
        return Result.fail(null);
    }

    /**
     * 博客热议
     * @return
     */
    @GetMapping("/commentBlogs")
    @ResponseBody
    public Result commentBlogs() {
        String WeekKey = "week:rank";
        ArrayList<Map> list = new ArrayList<>();
        //从大到小排列
        Set<ZSetOperations.TypedTuple> sets = redisTemplate.opsForZSet().reverseRangeWithScores(WeekKey,0,-1);
        for (ZSetOperations.TypedTuple set : sets) {
            HashMap<Object, Object> map = new HashMap<>();
            //储存博客id
            map.put("id",set.getValue());
            //储存博客标题
            map.put("title",redisTemplate.opsForHash().get("blogsCache"+set.getValue(),"blog:title"));
            //储存博客分数(博客评论数)
            map.put("score",set.getScore());

            list.add(map);
        }
        return Result.success(list);
    }

    /**
     * 查看博客详情
     * @return
     */
    @GetMapping("/blogDetail")
    @ResponseBody
    public Result blogDeatil(Integer id){
        System.out.println("id: "+id);
        //动态更新博客阅读量
        categoryService.setViewCounts(id);
        String blogCache = "blogsCache";
        Map<String, Object> map = new HashMap<>();
        //储存博客id
        map.put("id",redisTemplate.opsForHash().get("blogsCache"+id,"blog:ID"));
        //储存博客标题
        map.put("title",redisTemplate.opsForHash().get("blogsCache"+id,"blog:title"));
        //储存博客内容
        map.put("conmentCount",redisTemplate.opsForHash().get("blogsCache"+id,"blog:commentCount"));
        //储存博客分数(博客评论数)
        map.put("content",redisTemplate.opsForHash().get("blogsCache"+id,"blog:content"));
        //博客查看次数
        map.put("viewCount",redisTemplate.opsForHash().get("blogsCache"+id,"blog:viewCount"));
        return Result.success(map);
    }
}
