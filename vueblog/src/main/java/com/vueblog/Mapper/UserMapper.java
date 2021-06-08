package com.vueblog.Mapper;

import com.vueblog.VO.userVO;
import com.vueblog.pojo.MPost;
import com.vueblog.pojo.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author: haojie
 * @qq :1422471205
 * @CreateTime: 2021-02-02-17-05
 */
@Mapper
public interface UserMapper {
    /**
     * 用户名登录查询用户
     * @param username
     * @return
     */
    User userLogin(String username);

    /**
     * 根据查询自己发表的贴
    * @param id
     * @return
     */
    List<MPost> myBlogs(Integer id);

    /**
     * 查询自己收藏的博客
     * @param id
     * @return
     */
    List<MPost> getCollections(Integer id);

    /**
     * 查看当前用户角色信息
     * @param userName
     * @return
     */
    userVO getRoles(String userName);
}
