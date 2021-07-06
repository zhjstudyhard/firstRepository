package cn.xydata.service.impl;

import cn.xydata.common.exception.CustomException;
import cn.xydata.dto.UserLoginDto;
import cn.xydata.entity.UserEntity;
import cn.xydata.mapper.PermissionMapper;
import cn.xydata.mapper.RequestPathMapper;
import cn.xydata.mapper.RolesMapper;
import cn.xydata.mapper.UserMapper;
import cn.xydata.service.CacheService;
import cn.xydata.service.LoginUserService;
import cn.xydata.service.RolesService;
import cn.xydata.vo.LoginUserVo;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tk.mybatis.mapper.entity.Example;

import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * @Author: haojie
 * @qq :1422471205
 * @CreateTime: 2021-07-05-10-13
 */
@Service
public class LoginUserServiceImpl implements LoginUserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RolesMapper rolesMapper;

    @Autowired
    private PermissionMapper permissionMapper;

    @Autowired
    private RequestPathMapper requestPathMapper;

    @Autowired
    private RedisTemplate<String,LoginUserVo> redisTemplate;

    @Autowired
    private CacheService cacheService;
    /**
     * 用户登录
     *
     * @param userLoginDto
     * @param response
     */
    @Override
    public void loginUser(UserLoginDto userLoginDto, HttpServletResponse response) throws Exception {
        //构建查询条件
        Example example = new Example(UserEntity.class);
        example.createCriteria().andEqualTo("username", userLoginDto.getUsername())
                .andEqualTo("isDeleted", 0);

        //校验用户名密码
        UserEntity userEntity = userMapper.selectOneByExample(example);
        if (userEntity == null) {
            throw new CustomException("用户名或密码重复");
        }

        if (userEntity.getPassword().equals(DigestUtils.md5Hex(userLoginDto.getPassword().getBytes()))) {
            //转换VO
            LoginUserVo loginUserVo = userLoginDto.getVo(LoginUserVo.class);
            String token = UUID.randomUUID().toString().trim().replaceAll("-", "");
            loginUserVo.setToken(token);
            //获取当前用户的角色信息
            loginUserVo.setRoles(rolesMapper.selectRoles(userEntity.getId()));
            //获取当前用户的权限
            loginUserVo.setAuths(permissionMapper.selectPermissions(userEntity.getId()));
            //获取当前用户的Urls
            loginUserVo.setUrls(requestPathMapper.selectRequestPaths(userEntity.getId()));

            //返回客户端
            response.setHeader("Authorization", token);

            //存储redis缓存
            cacheService.put(token,loginUserVo);

//            redisTemplate.opsForValue().set(token,loginUserVo);
//            redisTemplate.opsForHash().put(token, "user:username", loginUserVo.getUsername());
//            redisTemplate.opsForHash().put(token, "user:roles", loginUserVo.getRoles());
//            redisTemplate.opsForHash().put(token, "user:Auths", loginUserVo.getAuths());
//            redisTemplate.opsForHash().put(token, "user:urls", loginUserVo.getUrls());

            System.out.println("loginUserVo1: " + cacheService.get(token));
        } else {
            throw new CustomException("用户名或密码错误");
        }
    }


    /**
     * 用户登出
     * @param token
     * @throws Exception
     */
    @Override
    public void logout(String token){
        //缓存清除
        cacheService.remove(token);
    }

}
