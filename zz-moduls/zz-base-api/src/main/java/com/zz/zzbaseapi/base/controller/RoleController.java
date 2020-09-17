package com.zz.zzbaseapi.base.controller;

import com.zz.Backtrack;
import com.zz.domain.PageData;
import com.zz.domain.ResultVO;
import com.zz.domain.authority.Role;
import com.zz.vo.view.RoleMenuView;
import com.zz.vo.view.TreeEntity;
import com.zz.zzbaseapi.base.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 角色
 * @author wqy
 * @version 1.0
 * @date 2020/6/11 15:12
 */
@Api(tags = "角色管理")
@Controller
@RequestMapping(value = "/rct")
public class RoleController {

    @Autowired
    private RoleService roleService;


    /**
     * 添加角色
     * @param name
     * @param description
     * @return
     */
    @ApiOperation(value = "添加角色")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "角色名称",name = "name",type = "String",required = true),
            @ApiImplicitParam(value = "描述",name = "description",type = "String",required = true),
    })
    @RequestMapping(value = "/addRole",method = {RequestMethod.POST})
    @ResponseBody
    public ResultVO<Boolean> addRole(String name, String description){

        if(null==roleService.addRole(name,description)){
            return Backtrack.errot("操作失败");
        }

        return Backtrack.success(true,"操作成功");
    }

    /**
     * 删除角色
     * @param rId
     * @return
     */
    @ApiOperation(value = "删除角色")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "角色ID",name = "rId",type = "Long",required = true),
    })
    @RequestMapping(value = "/deleteByRId",method = {RequestMethod.POST})
    @ResponseBody
    public ResultVO<Boolean> deleteByRId(Long rId){

        roleService.deleteByRId(rId);

        return Backtrack.success(true,"操作成功");

    }

    /**
     * 编辑角色
     * @param role
     * @return
     */
    @ApiOperation(value = "编辑角色")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "角色名称",name = "name",type = "String",required = true),
            @ApiImplicitParam(value = "描述",name = "description",type = "String",required = true),
    })
    @RequestMapping(value = "/updateRole",method = {RequestMethod.POST})
    @ResponseBody
    public ResultVO<Boolean> updateRole(Role role){

        roleService.updateRole(role);

        return Backtrack.success(true,"操作成功");

    }

    /**
     * 获取角色列表
     * @param currentPage 页码‘
     * @return
     */
    @ApiOperation(value = "获取角色列表")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "页码",name = "currentPage",type = "Integer",required = true),
    })
    @RequestMapping(value = "/getRoleMsgs",method = {RequestMethod.POST})
    @ResponseBody
    public ResultVO<PageData<Role>> getRoleMsgs(Integer currentPage){

        PageData<Role> list = roleService.getRoleMsgs(currentPage);

        if(null==list){
            return Backtrack.errot("操作失败");
        }

        return Backtrack.success(list,"操作成功");

    }

    /**
     * 获取角色列表LIST
     * @return
     */
    @ApiOperation(value = "获取角色列表LIST（不分页）")
    @RequestMapping(value = "/getRoleMsgsList",method = {RequestMethod.POST})
    @ResponseBody
    public ResultVO<List<Role>> getRoleMsgsList(){

        List<Role> list = roleService.getRoleMsgsList();

        if(null==list){
            return Backtrack.errot("操作失败");
        }

        return Backtrack.success(list,"操作成功");

    }

    /**
     * 获取tree对象
     * @return
     */
    @ApiOperation(value = "获取tree对象")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "角色ID",name = "rId",type = "Integer",required = true),
    })
    @RequestMapping(value = "/getNodes",method = {RequestMethod.POST})
    @ResponseBody
    public ResultVO<TreeEntity> getNodes(Long rId){

        TreeEntity list = roleService.getNodes(rId);

        if(null==list){
            return Backtrack.errot("操作失败");
        }

        return Backtrack.success(list,"操作成功");

    }

    /**
     * 保存角色菜单
     * @return
     */
    @ApiOperation(value = "保存角色菜单")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "角色ID",name = "rId",type = "Integer",required = true),
            @ApiImplicitParam(value = "选中菜单编码(逗号分隔)",name = "nodes",type = "String",required = true),
    })
    @RequestMapping(value = "/saveNodes",method = {RequestMethod.POST})
    @ResponseBody
    public ResultVO<String> saveNodes(Long rId, String nodes){

        String rs = roleService.saveNodes(rId, Arrays.stream(nodes.split(",")).collect(Collectors.toList()));

        if(null==rs){
            return Backtrack.errot("操作失败");
        }

        return Backtrack.success(null,"操作成功");

    }

    /**
     * 获取该角色绑定的菜单
     * @return
     */
    @ApiOperation(value = "获取该角色绑定的菜单")
    @PostMapping(value = "/getRoleMenuMsg")
    @ResponseBody
    public ResultVO<List<RoleMenuView>> getRoleMenuMsg(HttpServletRequest request){

        List<RoleMenuView> roleEntities = roleService.getRoleMenuMsg(request);

        if(null==roleEntities){
            return Backtrack.errot("操作失败");
        }

        return Backtrack.success(roleEntities);

    }





}
