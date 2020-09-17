package com.zz.security.access;
 
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zz.domain.ResultVO;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author WQY
 * @Date 2019/11/26 15:43
 * @Version 1.0
 */
@Component
public class TokenExceptionHandler implements AuthenticationEntryPoint {
 
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setContentType("application/json; charset=utf-8");
        // 直接返回 json错误
        ResultVO<Object> result = new ResultVO<>();
        //20，标识没有token
        result.setCode(20);
        result.setMsg("请求无效，没有有效token");
        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println(objectMapper.writeValueAsString(result));
        response.getWriter().write(objectMapper.writeValueAsString(result));
    }
}