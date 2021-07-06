package cn.xydata.config;

import cn.xydata.common.domain.result.ResponseData;
import cn.xydata.service.CacheService;
import cn.xydata.vo.LoginUserVo;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: haojie
 * @qq :1422471205
 * @CreateTime: 2021-06-29-14-00
 */
@Component
public class tokenFiflter implements Filter {
    @Autowired
    private RedisTemplate<String, LoginUserVo> redisTemplate;

    @Autowired
    private CacheService cacheService;

    /**
     * description: 忽略URL集合
     */
    private List<String> ignores = new ArrayList<>();


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        ignores.add("/api/login/loginUser");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("Fiflter开始执行-------------");
        //http请求
        HttpServletRequest req = (HttpServletRequest) request;
        System.out.println("当前URL请求" + req.getRequestURI());

        if (ignores.contains(req.getRequestURI())) {
            chain.doFilter(request, response);
        } else {
            //请求头获取token
            String token = req.getHeader("Authorization");
            //redis获取URL信息进行校验
//            LoginUserVo loginUserVo = redisTemplate.opsForValue().get(token);
            LoginUserVo loginUserVo = (LoginUserVo)cacheService.get(token);
            if (loginUserVo == null) {
                //错误校验
                response.setContentType("application/json; charset=utf-8");
                PrintWriter out = response.getWriter();
                out.write(JSON.toJSONString(new ResponseData("999999", "无效的token")));
            } else {
                boolean flag = true;
                for (String url : loginUserVo.getUrls()) {
                    System.out.println("拥有的URL："+loginUserVo.getUrls());
                    if (PathMatcherUtil.match(url, req.getRequestURI())) {
                        chain.doFilter(request, response);
                        flag = false;
                        break;
                    }
                }
                //url无权访问
                if (flag) {
                    response.setContentType("application/json; charset=utf-8");
                    PrintWriter out = response.getWriter();
                    out.write(JSON.toJSONString(new ResponseData("999999", "无权访问")));
                }
            }
        }

    }

    @Override
    public void destroy() {

    }
}
