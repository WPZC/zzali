package com.zz.zzjwt.controller;

import com.zz.region.domain.authority.Role;
import com.zz.region.domain.authority.User;
import com.zz.region.methods.Backtrack;
import com.zz.region.methods.ead.EAD;
import com.zz.region.vo.ResultVO;
import com.zz.security.service.CustomUserDetailsServiceImpl;
import com.zz.security.utils.jwt.JwtUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author WQY
 * @Date 2019/11/26 15:42
 * @Version 1.0
 */
@Api(tags = "登陆")
@Controller
public class UserController{

    @Resource
    private CustomUserDetailsServiceImpl customUserDetailsService;

    @ApiOperation(value = "获取token")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "用户名",name = "username",dataType = "String",required = true),
            @ApiImplicitParam(value = "密码",name = "password",dataType = "String",required = true),
    })
    @RequestMapping(value = "/token",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public ResultVO<String> login(@RequestParam String username, @RequestParam String password) {

        System.out.println("获取token");

        if(null==username||username.equals("")){
            return Backtrack.errot("用户名不能为空");
        }

        User user = customUserDetailsService.findByUserName(username, EAD.encode());

        if (user == null || !user.getPassword().equals(password)) {
            return Backtrack.errot("用户名或密码错误");
        }

        //Jwts.
        //ResultVO<Object> success = new ResultVO<>();
        //用户名密码正确，生成token给客户端
        //success.setCode(0);
        List<Role> roles = customUserDetailsService.findByUserRole(user.getId());
        //success.setData(JwtUtil.generateToken(username, roles));

        return Backtrack.success(JwtUtil.generateToken(username, roles),null);

    }
}