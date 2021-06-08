package com.vueblog.Service.Impl;

import com.vueblog.Mapper.UserMapper;
import com.vueblog.Service.UserService;
import com.vueblog.VO.userVO;
import com.vueblog.pojo.MPost;
import com.vueblog.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: haojie
 * @qq :1422471205
 * @CreateTime: 2021-02-02-17-08
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    /**
     * 用户名登录查询用户
     *
     * @param username
     * @return
     */
    @Override
    public User userLogin(String username) {
        return userMapper.userLogin(username);
    }

    /**
     * 查询自己发表的博客
     *
     * @param id
     * @return
     */
    @Override
    public List<MPost> myBlogs(Integer id) {
        return userMapper.myBlogs(id);
    }

    /**
     * 查询自己收藏的博客
     *
     * @param id
     * @return
     */
    @Override
    public List<MPost> getCollections(Integer id) {
        return userMapper.getCollections(id);
    }

    /**
     * 查看当前用户角色信息
     *
     * @param userName
     * @return
     */
    @Override
    public userVO getRoles(String userName) {

        return userMapper.getRoles(userName);
    }


}
