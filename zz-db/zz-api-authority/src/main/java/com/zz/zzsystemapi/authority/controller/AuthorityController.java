package com.zz.zzsystemapi.authority.controller;

import com.zz.zzsystemapi.authority.globalregister.GlobalController;
import com.zz.zzsystemapi.commondb.entity.RoleEntity;
import com.zz.zzsystemapi.commondb.entity.UserEntity;
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
public class AuthorityController extends GlobalController {

    @RequestMapping(value = "/findByUserName",method = {RequestMethod.POST})
    @ResponseBody
    public UserEntity findByUserName(@RequestParam("username") String username){

        return userService.findByUserName(username);

    }

    @PostMapping(value = "/findByUserRole")
    @ResponseBody
    public List<RoleEntity> findByUserRole(@RequestParam("id") Long id){

        return roleService.findByUserRole(id);

    }

}
