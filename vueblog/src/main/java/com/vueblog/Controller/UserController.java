package com.vueblog.Controller;

import com.vueblog.Mapper.UserMapper;
import com.vueblog.Service.MessageService;
import com.vueblog.Service.UserService;
import com.vueblog.VO.userVO;
import com.vueblog.pojo.MPost;
import com.vueblog.pojo.Result;
import com.vueblog.pojo.User;
import com.vueblog.untils.JWTuntils;
import lombok.AllArgsConstructor;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.jta.WebSphereUowTransactionManager;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Security;
import java.sql.ResultSet;
import java.util.*;

/**
 * @Author: haojie
 * @qq :1422471205
 * @CreateTime: 2021-02-02-17-09
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private MessageService messageService;

    /**
     * 用户登录
     * @param username
     * @param password
     * @param response
     * @return
     */
    @PostMapping("/login")
    @ResponseBody
    public Result login(String username,String password,HttpServletResponse response){
        User user = userService.userLogin(username);
        if(!password.equals(user.getPassword())){
            return Result.fail(null);
        }
        Map<String, String> map = new HashMap<>();
        map.put("username",user.getUsername());
        map.put("userId",String.valueOf(user.getId()));
        String token = JWTuntils.getToken(map);
        System.out.println("生成的token: "+token);
        //响应生成请求头
        response.setHeader("Authorization",token);
        //暴露请求头信息
        response.setHeader("Access-control-Expose-Headers", "Authorization");
        return Result.success(user);
    }

    /**
     * 用户登出
     * @return
     */
    @GetMapping("/logout")
    @ResponseBody
    public Result logout(){
        Subject subject = SecurityUtils.getSubject();
        boolean authenticated = subject.isAuthenticated();
        if(!authenticated){
           return Result.fail(null);
        }
        subject.logout();
        return Result.success(null);
    }

    /**
     * 查询自己发表的博客
     * @param
     * @return
     */
    @GetMapping("/myblogs")
    @ResponseBody
    public Result myBlogs(HttpServletRequest request){
        //从请求头获取token
        String token = request.getHeader("Authorization");
        //获取userId
        String userId = JWTuntils.getUserId(token);
        int id = Integer.parseInt(userId);
        List<MPost> mPosts = userService.myBlogs(id);
        return Result.success(mPosts);
    }

    /**
     * 查看自己收藏的博客
     * @param request
     * @return
     */
    @GetMapping("/getCollectionBlogs")
    @ResponseBody
    public Result getCollectBlogs(HttpServletRequest request){
        String token = request.getHeader("Authorization");
        String userid = JWTuntils.getUserId(token);
        int userId = Integer.parseInt(userid);
        List<MPost> collections = userService.getCollections(userId);
        return Result.success(collections);
    }


    /**
     * 查询当前用户角色信息
     * @return
     */
    @GetMapping("/getRoles")
    @ResponseBody
    public Result getRoles(){
        String token = (String)SecurityUtils.getSubject().getPrincipal();
        String userName = JWTuntils.getUserName(token);
        userVO user = userService.getRoles(userName);
        HashMap<String, Object> map = new HashMap<>();
        //封装当前用户的角色信息
        map.put("roles",user.getRoles());
        //返回数据map集
        return Result.success(map);
    }

    /**
     * 查看未读消息数量
     * @return
     */
    @RequestMapping("/read")
    @ResponseBody
    public Result read(){
        String token = (String)SecurityUtils.getSubject().getPrincipal();
        Integer userId = Integer.parseInt(JWTuntils.getUserId(token));
        Integer count = messageService.noReadMessage(userId);
        return Result.success(count);
    }
}
