package com.zz.security.access;
 
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zz.domain.ResultVO;
import org.springframework.security.access.AccessDeniedException;
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
public class AccessDeniedHandler implements org.springframework.security.web.access.AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setContentType("application/json; charset=utf-8");
        // 返回我们的自定义json
        ObjectMapper objectMapper = new ObjectMapper();
        ResultVO<Object> result = new ResultVO<>();
        //50，标识有token，但是该用户没有权限
        result.setCode(50);
        result.setMsg("请求无效，用户没有权限");
        System.out.println(objectMapper.writeValueAsString(result));
        response.getWriter().write(objectMapper.writeValueAsString(result));
    }
}