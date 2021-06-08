package com.vueblog.Service;

import com.vueblog.VO.userVO;
import com.vueblog.pojo.MPost;
import com.vueblog.pojo.User;

import java.util.List;

/**
 * @Author: haojie
 * @qq :1422471205
 * @CreateTime: 2021-02-02-17-07
 */
public interface UserService {
    /**
     * 用户名登录查询用户
     * @param username
     * @return
     */
    public User userLogin(String username);

    /**
     * 查询自己发表的博客
     * @param id
     * @return
     */
    public List<MPost> myBlogs(Integer id);

    /**
     * 查询自己收藏的博客
     * @param id
     * @return
     */
    public List<MPost> getCollections(Integer id);

    /**
     * 查看当前用户角色信息
     * @param userName
     * @return
     */
    userVO getRoles(String userName);


}
