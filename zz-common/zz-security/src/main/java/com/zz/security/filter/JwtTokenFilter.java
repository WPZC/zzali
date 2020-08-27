package com.zz.security.filter;

import com.zz.security.domain.AuthUser;
import com.zz.security.utils.jwt.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author WQY
 * @Date 2019/11/26 15:44
 * @Version 1.0
 */
@Slf4j
@Component
public class JwtTokenFilter extends OncePerRequestFilter {
 
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        log.info("请求路径 : " + request.getRequestURL());
        log.info("请求方式 : " + request.getMethod());

        //先从header中获取token
        String token = request.getHeader("token");

        //如果消息头中的token为null或空，则从参数中获取
        if(null==token||token.equals("")){
            token = request.getParameter("token");
        }

        //获取token，并且解析token，如果解析成功，则放入 SecurityContext
        if (token != null&&token.length()>=32) {
            try {
                AuthUser authUser = JwtUtil.parseToken(token);
                //如果此处不放心解析出来的 authuser，可以再从数据库查一次，验证用户身份：
                //解析成功
                if (SecurityContextHolder.getContext().getAuthentication() == null) {
                    //我们依然使用原来filter中的token对象
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(authUser, null, authUser.getAuthorities());

                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                }
            } catch (Exception e) {
                log.info("解析失败，可能是伪造的或者该token已经失效了（我们设置失效5分钟）。");
            }
        }

        filterChain.doFilter(request, response);
    }
}
 