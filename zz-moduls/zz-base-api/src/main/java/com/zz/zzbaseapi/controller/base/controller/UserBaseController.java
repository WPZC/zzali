package com.zz.zzbaseapi.controller.base.controller;

import com.zz.region.domain.PageData;
import com.zz.region.domain.authority.Role;
import com.zz.region.domain.authority.User;
import com.zz.region.methods.Backtrack;
import com.zz.region.vo.ResultVO;
import com.zz.security.utils.jwt.JwtUtil;
import com.zz.zzbaseapi.controller.base.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * 用户
 * @author
 * @version 1.0
 * @date 2020/6/11 16:35
 */
@Api(tags = "用户管理")
@Controller
@RequestMapping(value = "/uct")
public class UserBaseController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "添加用户")
    @ApiImplicitParam(value = "角色ID",name = "roleId",dataType = "Long",required = true)
    @RequestMapping(value = "/addUser",method = {RequestMethod.POST})
    @ResponseBody
    public ResultVO<User> addUser(User user, Long roleId){

        try {
            user.setSavetime(new Date());
            String rs = userService.addUser(user,roleId);

            if(rs.equals("success")){
                return Backtrack.success(user,"操作成功");
            }

           return Backtrack.errot("操作失败");
        }catch (Exception e){
            e.printStackTrace();
            if(e instanceof RuntimeException ){
                return Backtrack.errot(e.getMessage());
            }
            return Backtrack.errot("操作失败");
        }


    }

    @ApiOperation(value = "修改用户")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "角色ID",name = "roleId",dataType = "Long",required = true),
            @ApiImplicitParam(value = "id",name = "id",dataType = "Long",required = true),
    })
    @RequestMapping(value = "/updateUser",method = {RequestMethod.POST})
    @ResponseBody
    public ResultVO<User> updateUser(User user, Long roleId){

       try {
           String rs =  userService.updateUser(user,roleId);
            if("success".equals(rs)){
                return Backtrack.success(user,"操作成功");
            }
            return Backtrack.errot("操作失败");
        }catch (Exception e){
            e.printStackTrace();
           if(e instanceof RuntimeException ){
               return Backtrack.errot(e.getMessage());
           }
            return Backtrack.errot("操作失败");
        }
    }

    /**
     * 获取用户列表
     * @param currentPage 页码‘
     * @return
     */
    @ApiOperation(value = "获取用户列表")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "页码",name = "currentPage",dataType = "Integer",required = true),
    })
    @RequestMapping(value = "/findByPage",method = {RequestMethod.POST})
    @ResponseBody
    public ResultVO<PageData<User>> list(Integer currentPage){

        PageData<User> list = userService.findByPage(currentPage);

        if(null==list){
            return Backtrack.errot("操作失败");
        }

        return Backtrack.success(list,"操作成功");

    }

    /**
     * 删除用户
     * @param id
     * @return
     */
    @ApiOperation(value = "删除用户")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "id",name = "id",dataType = "Integer",required = true),
    })
    @RequestMapping(value = "/deleteById",method = {RequestMethod.POST})
    @ResponseBody
    public ResultVO deleteById(Long id){

        userService.deleteByuId(id);

        return Backtrack.success(true,"操作成功");

    }

    /**
     * 获取该用户的角色
     * @param id
     * @return
     */
    @ApiOperation(value = "获取该用户的角色")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "id",name = "id",dataType = "Long",required = true),
    })
    @RequestMapping(value = "/oleRole",method = {RequestMethod.POST})
    @ResponseBody
    public ResultVO<Role> oleRole(Long id){

        try {
            Role role =  userService.oleRole(id);
            if(role !=null){
                return Backtrack.success(role,"操作成功");
            }
            return Backtrack.errot("操作失败");
        }catch (Exception e){
            return Backtrack.errot("操作失败");
        }
    }

    @ApiOperation(value = "获取用户信息")
    @RequestMapping(value = "/gerUser",method = {RequestMethod.POST})
    @ResponseBody
    public ResultVO<User> gerUser(HttpServletRequest request){

        try {
            return Backtrack.success(userService.getUser(JwtUtil.parseToken(request.getHeader("token")).getUsername()));
        }catch (Exception e){
            return Backtrack.errot("操作失败");
        }
    }
}
