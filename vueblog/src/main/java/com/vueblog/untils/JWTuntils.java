package com.vueblog.untils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import javax.naming.AuthenticationException;
import java.util.Calendar;
import java.util.Map;

/**
 * @Author: haojie
 * @qq :1422471205
 * @CreateTime: 2021-02-02-17-20
 */
public class JWTuntils {
    //创建盐值
    public static final String salt = "123";

    //创建token
    public static String getToken(Map<String,String> map){

        //设置过期时间
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.SECOND,3600*24);
        //创建jwt生成器
        JWTCreator.Builder builder = JWT.create();
        for(String key : map.keySet()){
            builder.withClaim(key,map.get(key));
        }
        String token = builder.withExpiresAt(instance.getTime())
                .sign(Algorithm.HMAC256(salt)).toString();
        return token;
    }

    //解析验证token
    public static boolean verify(String token) {
         //创建token解析器
        JWTVerifier build = JWT.require(Algorithm.HMAC256(salt)).build();
        try {
            DecodedJWT verify = build.verify(token);
            return true;
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    //获取用户名
    public static String getUserName(String token){
        DecodedJWT decode = JWT.decode(token);
        String username = decode.getClaim("username").asString();
        return username;
    }
    //获取用户名id
    public static String getUserId(String token){
        DecodedJWT decode = JWT.decode(token);
        String userId = decode.getClaim("userId").asString();
        return userId;
    }
}
