package com.example.financialsystem.common;

import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.financialsystem.entity.User;
import com.example.financialsystem.exception.ServiceException;
import com.example.financialsystem.mapper.UserMapper;
import org.springframework.http.HttpMethod;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 功能:
 * 作者: HTSANDORI
 * 日期: 2023/12/15 16:12
 */

public class JwtInterceptor implements HandlerInterceptor {

    @Resource
    private UserMapper userMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        //静态资源数组
        List<String> staticResources = new ArrayList<>();
        staticResources.add("/upload/**");
        // 获取当前请求的路径
        String requestURI = request.getRequestURI();
        AntPathMatcher matcher = new AntPathMatcher();
        for (String resource : staticResources) {
            if (matcher.match(resource, requestURI)) {
                return true; // 是静态资源，跳过JWT验证
            }
        }

        // 跳过 OPTIONS 请求
        if (request.getMethod().equals(HttpMethod.OPTIONS.name())) {
            return true;
        }

        String token = request.getHeader("token"); //从header里面传过来的参数
        if (StrUtil.isBlank(token)) {
            token = request.getParameter("Token"); //url参数
        }
        // 如果请求头和URL参数里面都没有token,报错401  执行认证
        if (StrUtil.isBlank(token)) {
            throw new ServiceException("401", "请登录");
        }
        // 获取 token 中的 user id
        String userId;
        try {
            userId = JWT.decode(token).getAudience().get(0); //解码token,拿到第一个参数
        } catch (JWTDecodeException j) {
            throw new ServiceException("401", "请登录");
        }
        // 根据token中的userid查询数据库
        User user = userMapper.selectById(Integer.valueOf(userId));
        if (user == null) {
            throw new ServiceException("401", "请登录");
        }
        // 用户密码加签验证 token
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(user.getPassword())).build();
        try {
            jwtVerifier.verify(token); // 验证token
        } catch (JWTVerificationException e) {
            throw new ServiceException("401", "请登录");
        }
        return true;
    }
}