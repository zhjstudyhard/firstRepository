package com.vueblog.Controller;

import com.vueblog.Service.CommentService;
import com.vueblog.Service.MessageService;
import com.vueblog.Service.PostService;
import com.vueblog.VO.CommentVO;
import com.vueblog.pojo.*;
import com.vueblog.untils.JWTuntils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * @Author: haojie
 * @qq :1422471205
 * @CreateTime: 2021-03-12-14-57
 */
@Controller
@RequestMapping("/Comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @Autowired
    private PostService postService;

    @Autowired
    private MessageService messageService;
    /**
     * 博客评论
     * @param request
     * @param comment
     * @return
     */
    @PostMapping("/rely")
    @ResponseBody
    public Result Comment(HttpServletRequest request, Comment comment){
        System.out.println("接受参数："+comment);
        //获取当前用户的id
        String token = request.getHeader("Authorization");
        Integer userId = Integer.parseInt(JWTuntils.getUserId(token));

        comment.setCreated(new Date());
        comment.setUserId(userId);

        Boolean flag = commentService.relyComment(comment);
        if(flag){
            postService.AutomaticOne(comment.getPostId());
            //查询出当前评论的博客
            MPost mPost = postService.selectOne(comment.getPostId().intValue());
            if (!mPost.getUserID().equals(comment.getUserId())){
                UserMessage userMessage = new UserMessage();
                userMessage.setPostId(mPost.getId());
                userMessage.setFromUserId(userId);
                userMessage.setToUserId(userId);
                userMessage.setCommentId(mPost.getId());
                userMessage.setStatus(0);
                userMessage.setType(1);
                userMessage.setContent(comment.getContent());
                userMessage.setCreated(new Date());
                //保存到数据库中
                messageService.save(userMessage);

            }
            return Result.success(null);
        }
        return Result.fail(null);
    }

    /**
     * 查询博客评论
     * @param blogId
     * @return
     */
    @GetMapping("/getComments")
    @ResponseBody
    public Result commentBlog(Integer blogId,Integer pageNum){
        System.out.println("blog: "+blogId+"pageNum："+pageNum);
        Page<CommentVO> page = new Page<>();
        //查询当前博客的评论数量
        Integer counts = commentService.countComment(blogId);
        //设置博客的评论总数量
        page.setPageTotalCount(counts);
        //设置page的分页总数量
        page.setPageNums();
        //设置当前页码
        page.setPageNum(pageNum);
        //查询博客每页的数据
        List<CommentVO> comments = commentService.blogComment(blogId,((pageNum - 1) * Page.page_SIZE),Page.page_SIZE);
        page.setItems(comments);

        System.out.println("comments: "+page);
        if (comments != null) {
            for (CommentVO commentVO : comments) {
                System.out.println("id："+commentVO.getCommentId());
            }
            return Result.success(page);
        }
        return Result.fail(null);
    }

    /**
     * 删除博客评论
     * @param commentId
     * @param commentUserId
     * @return
     */
    @PostMapping("/deleteComment")
    @ResponseBody
    public Result deleteComment(Integer commentId,Integer commentUserId,HttpServletRequest request){
        System.out.println("获的参数："+commentId+" "+"commentUserId: "+commentUserId);
        String token = request.getHeader("Authorization");
        int userId = Integer.parseInt(JWTuntils.getUserId(token));
        //校验角色权限
        Subject subject = SecurityUtils.getSubject();
        boolean admin = subject.hasRole("admin");
        if (admin) {
            boolean flag = commentService.delComment(commentId);
            if (flag) {
                return Result.success(null);
            }
        }
        if (userId == commentUserId){
            boolean flag = commentService.delComment(commentId);
            if (flag) {
                return Result.success(null);
            }
        }
        return Result.fail(null);
    }
}
