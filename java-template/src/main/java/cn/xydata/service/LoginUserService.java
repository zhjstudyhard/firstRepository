package cn.xydata.service;

import cn.xydata.dto.UserLoginDto;

import javax.servlet.http.HttpServletResponse;

/**
 * @Author: haojie
 * @qq :1422471205
 * @CreateTime: 2021-07-05-10-12
 */
public interface LoginUserService {


    /**
     * 用户登录
     * @param userLoginDto
     * @param response
     * @throws Exception
     */
    void loginUser(UserLoginDto userLoginDto, HttpServletResponse response) throws Exception;

    /**
     * 用户登出
     * @param token
     */
    void logout(String token);
}
