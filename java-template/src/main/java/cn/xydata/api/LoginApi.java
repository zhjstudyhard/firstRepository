package cn.xydata.api;

import cn.xydata.common.annotation.Error;
import cn.xydata.dto.UserLoginDto;
import cn.xydata.service.LoginUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * @Author: haojie
 * @qq :1422471205
 * @CreateTime: 2021-07-05-09-49
 */
@RestController
@RequestMapping("/api/login/")
public class LoginApi {

    @Autowired
    private LoginUserService loginUserService;

    /**
     * 用户登录
     *
     * @return
     * @throws Exception
     */
    @Error
    @RequestMapping(value = "/loginUser", method = RequestMethod.POST)
    public Object loginUser(@Valid @RequestBody UserLoginDto userLoginDto, HttpServletResponse response) throws Exception{
        //调用业务层
        loginUserService.loginUser(userLoginDto,response);
        return null;
    }
}
