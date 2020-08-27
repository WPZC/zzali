package com.zz.zzjwt.controller;

import com.zz.region.domain.authority.RoleEntity;
import com.zz.region.domain.authority.UserEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.zz.security.utils.jwt.JwtUtil;
import com.zz.security.service.CustomUserDetailsServiceImpl;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author WQY
 * @Date 2019/11/26 15:42
 * @Version 1.0
 */
@Controller
public class UserController{

    @Resource
    private CustomUserDetailsServiceImpl customUserDetailsService;

    @RequestMapping(value = "/token",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public String login(@RequestParam String username, @RequestParam String password) {

        UserEntity user = customUserDetailsService.findByUserName(username);

        //Jwts.
        //ResultVO<Object> success = new ResultVO<>();
        //用户名密码正确，生成token给客户端
        //success.setCode(0);
        List<RoleEntity> roles = customUserDetailsService.findByUserRole(user.getId());
        //success.setData(JwtUtil.generateToken(username, roles));

        return JwtUtil.generateToken(username, roles);

    }
}