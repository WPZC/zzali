package com.zz.zzbaseapi.controller.base.controller;

import com.zz.region.domain.authority.Role;
import com.zz.region.domain.authority.User;
import com.zz.region.methods.ead.EAD;
import com.zz.zzbaseapi.controller.base.service.RoleService;
import com.zz.zzbaseapi.controller.base.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author wqy
 * @version 1.0
 * @date 2020/5/28 11:09
 */
@Controller
@RequestMapping(value = "/ac")
public class AuthorityController{

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @RequestMapping(value = "/findByUserName",method = {RequestMethod.POST})
    @ResponseBody
    public User findByUserName(@RequestParam("username") String username,@RequestParam("encode") String encode){

        /**
         * 由于该接口特殊，所以进行了二次加密验证
         */
        if(!EAD.decode(encode)){
            return null;
        }

        return userService.findByUserName(username);

    }

    @PostMapping(value = "/findByUserRole")
    @ResponseBody
    public List<Role> findByUserRole(@RequestParam("id") Long id,@RequestParam("encode") String encode){
        /**
         * 由于该接口特殊，所以进行了二次加密验证
         */
        if(!EAD.decode(encode)){
            return null;
        }
        return roleService.findByUserRole(id);

    }

}
