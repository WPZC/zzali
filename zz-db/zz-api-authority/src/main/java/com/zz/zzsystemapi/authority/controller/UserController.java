package com.zz.zzsystemapi.authority.controller;

import com.zz.region.domain.PageData;
import com.zz.region.methods.Backtrack;
import com.zz.region.vo.ResultVO;
import com.zz.zzsystemapi.authority.globalregister.GlobalController;
import com.zz.zzsystemapi.commondb.entity.RoleEntity;
import com.zz.zzsystemapi.commondb.entity.UserEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 用户
 *
 * @author
 * @version 1.0
 * @date 2020/6/11 16:35
 */
@Controller
@RequestMapping(value = "/uct")
public class UserController extends GlobalController {

    @RequestMapping(value = "/addUser", method = {RequestMethod.POST})
    @ResponseBody
    public ResultVO<UserEntity> addUser(@RequestBody UserEntity userEntity, @RequestParam("roleId")Long roleId) {

        try {
            //根据用户名查询是用户是否存在。
            //存在报用户名重复提示。不存在保存用户
            UserEntity old =  userService.findByUserName(userEntity.getUsername());
            if(old != null){
                return Backtrack.errot("用户名已经存在！");
            }
            String rs = userService.addUser(userEntity,roleId);

            return Backtrack.success(rs);

        } catch (Exception e) {
            e.printStackTrace();
            return Backtrack.errot("操作失败");
        }

    }

    /**
     * 修改用户
     *
     * @param userEntity
     * @return
     */
    @PostMapping(value = "/updateUser")
    @ResponseBody
    public ResultVO<String> updateUser(@RequestBody UserEntity userEntity, @RequestParam("roleId")Long roleId) {
        /**
         * 根据id查询用户是否存在
         */
        UserEntity old = userService.findById(userEntity.getId());

        if (old == null) {
            return Backtrack.errot("用户不存在");
        }

        //判断该对象要更新的用户名是否一致？
        //一致直接更新，否判断要更新的用户名在库中是否存在？
        //存在提示用户名已存在，否直接更新。
        if(!old.getUsername().equals(userEntity.getUsername())){
            UserEntity user =  userService.findByUserName(userEntity.getUsername());
            if(user != null){
                return Backtrack.errot("用户名已经存在！");
            }
        }
        return Backtrack.success(userService.updateUser(userEntity, roleId));

    }

    /**
     * 获取用户列表
     *
     * @param currentPage
     * @return
     */
    @PostMapping(value = "/findByPage")
    @ResponseBody
    public ResultVO<PageData<UserEntity>> findByPage(@RequestParam("currentPage") Integer currentPage) {

        return Backtrack.success(userService.findByPage(currentPage));

    }

    /**
     * 删除角色
     * @param id
     * @return
     */
    @PostMapping(value = "/deleteByuId")
    @ResponseBody
    public ResultVO<String> deleteByuId(@RequestParam("id") Long id){

        /**
         * 根据id查询用户是否存在
         */
        UserEntity old = userService.findById(id);

        if (old == null) {
            return Backtrack.errot("用户不存在");
        }


        String rs = userService.deleteByuId(id);

        if("删除成功".equals(rs)){
            return Backtrack.success("删除成功");
        }
        return Backtrack.errot(rs);

    }


    @RequestMapping(value = "/oleRole", method = {RequestMethod.POST})
    @ResponseBody
    public RoleEntity oleRole(@RequestParam("id") Long id) {
        return userService.oleRole(id);

    }

}
