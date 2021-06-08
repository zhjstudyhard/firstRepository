package com.vueblog.Controller;

import com.vueblog.Service.PostService;
import com.vueblog.VO.CollectionVO;
import com.vueblog.VO.postVO;
import com.vueblog.pojo.Collection;
import com.vueblog.pojo.MPost;
import com.vueblog.pojo.Result;
import com.vueblog.search.mq.PostMqIndexMessage;
import com.vueblog.untils.JWTuntils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * @Author: haojie
 * @qq :1422471205
 * @CreateTime: 2021-03-05-20-35
 */
@Controller
@RequestMapping("/Post")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 发布博客
     *
     * @param post
     * @param request
     * @return
     */
    @PostMapping("/edit")
    @ResponseBody
    public Result blogsEdit(MPost post, HttpServletRequest request) {
        System.out.println("博客参数：" + post);
        String token = request.getHeader("Authorization");
        Integer userId = Integer.parseInt(JWTuntils.getUserId(token));

        post.setUserID(userId);
        post.setVoteUp(0);
        post.setVoteDown(0);
        post.setViewCount(0);
        post.setCommentCount(0);
        post.setModified(new Date());
        post.setCreated(new Date());
        post.setLevel(1);
        post.setRecommend(false);
        System.out.println("设置后的博客参数：" + post);

        Boolean aBoolean = postService.blogsEdit(post);
//        System.out.println("postId: "+post.getId());
        if (aBoolean) {
            //发送消息队列
            rabbitTemplate.convertAndSend("ems", PostMqIndexMessage.CREATE, new PostMqIndexMessage(post.getId(), PostMqIndexMessage.CREATE));
            return Result.success(null);
        }
        return null;
    }

    /**
     * 查看置顶博客
     *
     * @return
     */
    @GetMapping("/levelBlogs")
    @ResponseBody
    public Result levelBlogs() {
        List<postVO> mPosts = postService.levelBlogs();
        return Result.success(mPosts);
    }


    /**
     * 查看博客详情
     *
     * @param BlogId
     * @return
     */
    @GetMapping("/detailBlog")
    @ResponseBody
    public Result detailBlofg(Integer BlogId) {
        System.out.println("接受的blogId: " + BlogId);
        postVO postVO = postService.detailBlogsEdit(BlogId);
        return Result.success(postVO);
    }

    /**
     * 删除博客
     *
     * @param postUserId
     * @param postId
     * @param request
     * @return
     */
    @PostMapping("/removeBlog")
    @ResponseBody
//    @RequiresRoles("")
    public Result removeBlog(Integer postUserId, Integer postId, HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        //获取登录用户的id
        Integer userId = Integer.parseInt(JWTuntils.getUserId(token));
        //判断该用户是否为admin角色
        if (SecurityUtils.getSubject().hasRole("admin")) {
            boolean b = postService.deleteBlogs(postId);
            if (b) {
                //发送消息队列
                rabbitTemplate.convertAndSend("ems", PostMqIndexMessage.REMOVE, new PostMqIndexMessage(postId, PostMqIndexMessage.REMOVE));
                return Result.success(null);
            }
            return Result.fail(null);
        }
        //判断当前登录用户是否为博客发布者
        if (!postUserId.equals(userId)) {
            return Result.fail(null);
        }
        boolean b = postService.deleteBlogs(postId);
        //发送消息队列
        rabbitTemplate.convertAndSend("ems", PostMqIndexMessage.REMOVE, new PostMqIndexMessage(postId, PostMqIndexMessage.REMOVE));
        return Result.success(null);
    }


    /**
     * 置顶博客
     *
     * @param BlogId
     * @return
     */
    @GetMapping("/setLevel")
    @ResponseBody
    public Result setLevel(Integer BlogId) {
        boolean b = postService.setLevel(BlogId);
        if (b) {
            return Result.success(null);
        }
        return Result.fail(null);

    }

    /**
     * 更新博客
     *
     * @param post
     * @return
     */
    @PostMapping("/updateBlog")
    @ResponseBody
    public Result update(MPost post) {
        System.out.println("接受的post: " + post);
        Boolean flag = postService.updateBlogs(post);
        if (flag) {
            //发送消息队列
            rabbitTemplate.convertAndSend("ems", PostMqIndexMessage.UPDATE, new PostMqIndexMessage(post.getId(), PostMqIndexMessage.REMOVE));
            return Result.success(null);
        }
        return Result.fail(null);
    }

    /**
     * 判断该博客是否被搜藏
     *
     * @param blogId
     * @return
     */
    @GetMapping("/getCollectionAssert")
    @ResponseBody
    public Result getCollectionAssert(Integer blogId, HttpServletRequest request) {
        //获取当前用户id
        String token = request.getHeader("Authorization");
        int userId = Integer.parseInt(JWTuntils.getUserId(token));

        Collection collection = postService.getCollectionAssert(blogId, userId);
        System.out.println("collection: " + collection);
        if (collection != null) {
            return Result.fail(null);
        }
        return Result.success(null);
    }

    /**
     * 收藏博客
     *
     * @param collectionVO
     * @param request
     * @return
     */
    @PostMapping("/collectionBlog")
    @ResponseBody
    public Result collectionBlogs(CollectionVO collectionVO, HttpServletRequest request) {

//        System.out.println("接受的collection: "+collectionVO);
        String token = request.getHeader("Authorization");
        Integer userId = Integer.parseInt(JWTuntils.getUserId(token));
        collectionVO.setUserId(userId);
        collectionVO.setId(null);
        collectionVO.setCreated(new Date());
        collectionVO.setModified(new Date());
        Boolean flag = postService.collectionBlogs(collectionVO);
        if (flag) {
            return Result.success(null);
        }
        return Result.fail(null);
    }


    /**
     * 判断修改博客是否有权限
     *
     * @param userId
     * @param request
     * @return
     */
    @GetMapping("/editBlog")
    @ResponseBody
    public Result editBlog(Integer userId, HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        Integer userId2 = Integer.parseInt(JWTuntils.getUserId(token));
        if (userId2.equals(userId)) {
            return Result.success(null);
        }
        return Result.fail(null);
    }

}
