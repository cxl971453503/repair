package com.chenxile.repair.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.chenxile.repair.util.JwtTokenUtils;
import com.chenxile.repair.util.Result;
import com.chenxile.repair.util.StatusCode;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Objects;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private StringRedisTemplate redisTemplate;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        /**
         * 从请求头里面获取token
         */
        String token = request.getHeader(JwtTokenUtils.TOKEN_HEADER);
//        System.out.println(token);
        if (StringUtils.isNotBlank(token)){
            /**
             * 根据token获取用户名
             * 再根据用户名从redis获得库中的token
             * 将两个token进行对比 相等则放行
             */
            String userid = JwtTokenUtils.getUserid(token);
            String sourceToken = redisTemplate.opsForValue().get(userid);
            if(StringUtils.isNotBlank(sourceToken)){
                if (Objects.equals(token,sourceToken)){
                    return true;
                }
            }
        }

        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");
        PrintWriter pw = response.getWriter();
        pw.println(objectMapper.writeValueAsString(Result.fail(StatusCode.USER_NOT_LOGIN)));
        return false;
    }
}
