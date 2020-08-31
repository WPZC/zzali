package com.zz.zzsystemapi.authority.controller;

import com.zz.region.domain.PageData;
import com.zz.region.methods.Backtrack;
import com.zz.region.vo.ResultVO;
import com.zz.zzsystemapi.authority.globalregister.GlobalController;
import com.zz.zzsystemapi.commondb.entity.Menu;
import com.zz.zzsystemapi.commondb.entity.RoleEntity;
import com.zz.zzsystemapi.commondb.entity.RoleMenu;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author wqy
 * @version 1.0
 * @date 2020/6/11 16:41
 */
@Controller
@RequestMapping(value = "/rct")
public class RoleController extends GlobalController {

    /**
     * 添加角色
     * @param name
     * @param description
     * @return
     */
    @PostMapping(value = "/addRole")
    @ResponseBody
    public ResultVO<RoleEntity> addRole(@RequestParam("name") String name, @RequestParam("description") String description){

        return Backtrack.success(roleService.addRole(name,description));

    }

    /**
     * 删除角色
     * @param rId
     * @return
     */
    @PostMapping(value = "/deleteByRId")
    @ResponseBody
    public ResultVO<String> deleteByRId(@RequestParam("rId") Long rId){

        String rs = roleService.deleteByRId(rId);

        if("删除成功".equals(rs)){
            return Backtrack.success("删除成功");
        }
        return Backtrack.errot(rs);


    }

    /**
     * 修改橘色
     * @param roleEntity
     * @return
     */
    @PostMapping(value = "/updateRole")
    @ResponseBody
    public ResultVO<RoleEntity> updateRole(@RequestBody RoleEntity roleEntity){

        return Backtrack.success(roleService.updateRole(roleEntity));

    }


    /**
     * 获取角色信息
     * @param currentPage
     * @return
     */
    @PostMapping(value = "/getRoleMsgs")
    @ResponseBody
    public ResultVO<PageData<RoleEntity>> getRoleMsgs(@RequestParam("currentPage") Integer currentPage){

        return Backtrack.success(roleService.getRoleMsgs(currentPage));

    }

    /**
     * 获取该用户的绑定关系
     * @return
     */
    @PostMapping(value = "/getRoleMenus")
    @ResponseBody
    public ResultVO<List<RoleMenu>> getRoleMenus(@RequestParam("r_id") Long r_id){

        List<RoleMenu> roleMenus = roleService.getRoleMenus(r_id);

        if(null==roleMenus){
            return Backtrack.errot("操作失败");
        }

        return Backtrack.success(roleMenus);
    }

    /**
     * 给角色绑定菜单
     * @param roleMenus
     * @return
     */
    @PostMapping(value = "/saveNodes")
    @ResponseBody
    public ResultVO<String> saveNodes(@RequestBody List<RoleMenu> roleMenus){

        String rs = roleService.saveNodes(roleMenus);

        if(null==roleMenus){
            return Backtrack.errot("操作失败");
        }

        return Backtrack.success(rs);

    }

    /**
     * 获取角色列表List
     * @return
     */
    @PostMapping(value = "/getRoleMsgsList")
    @ResponseBody
    public ResultVO<List<RoleEntity>> getRoleMsgsList(){

        List<RoleEntity> roleEntities = roleService.getRoleMsgsList();

        if(null==roleEntities){
            return Backtrack.errot("操作失败");
        }

        return Backtrack.success(roleEntities);

    }


    /**
     * 根据用户id获取用户绑定的菜单
     * @param id
     * @return
     */
    @PostMapping(value = "/getMenuByRId")
    @ResponseBody
    ResultVO<List<Menu>> getMenuByRId(@RequestParam("id") Long id){

        List<Menu> menus = roleService.getMenuByRId(id);

        return Backtrack.success(menus);
    }
}
