package com.hans.filter;

import com.alibaba.fastjson.JSON;
import com.hans.constants.SystemConstant;
import com.hans.domain.entity.LoginUser;
import com.hans.domain.entity.User;
import com.hans.enums.AppHttpCodeEnum;
import com.hans.response.ResponseResult;
import com.hans.utils.JwtUtil;
import com.hans.utils.RedisCache;
import com.hans.utils.WebUtils;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private RedisCache redisCache;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        //获取请求头中的token
        String token = httpServletRequest.getHeader("token");
        if(!StringUtils.hasText(token)){
            //说明该接口不需要登录  直接放行
            filterChain.doFilter(httpServletRequest,httpServletResponse);
            return;
        }
        String UserId = "";
        //解析获取userid
        try {
            Claims claims = JwtUtil.parseJWT(token);
            UserId = claims.getSubject();
        } catch (Exception e) {
            e.printStackTrace();
            //token超时  token非法
            //需要告诉前段，重新登录
            ResponseResult result = ResponseResult.errorResult(AppHttpCodeEnum.NEED_LOGIN);
            WebUtils.renderString(httpServletResponse, JSON.toJSONString(result));
            return;
        }
        //从Redis中获取用户信息
        LoginUser loginUser = redisCache.getCacheObject(SystemConstant.REDIS_KEY + UserId);
        if (Objects.isNull(loginUser)){
//            说明登录过期 提示重新登录
//            redis找不到，出问题了，哈哈
            ResponseResult result = ResponseResult.errorResult(AppHttpCodeEnum.NEED_LOGIN);
            WebUtils.renderString(httpServletResponse, JSON.toJSONString(result));
            return;

        }


        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(loginUser, null, null);
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

        filterChain.doFilter(httpServletRequest,httpServletResponse);


        //存入SecurityContextHolder
    }
}
