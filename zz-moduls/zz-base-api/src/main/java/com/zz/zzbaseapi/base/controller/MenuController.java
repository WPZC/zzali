package com.zz.zzbaseapi.base.controller;

import com.zz.Backtrack;
import com.zz.domain.PageData;
import com.zz.domain.ResultVO;
import com.zz.domain.authority.Menu;
import com.zz.zzbaseapi.base.service.MenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 菜单管理
 * @author wqy
 * @version 1.0
 * @date 2020/6/9 16:35
 */
@Api(value = "菜单管理",tags = "菜单管理")
@Controller
@RequestMapping(value = "/mct")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @ApiOperation(value = "添加菜单")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "父菜单编码",name = "parentCode",type = "String",required = true),
            @ApiImplicitParam(value = "菜单名称",name = "menuName",type = "String",required = true),
            @ApiImplicitParam(value = "路由",name = "router",type = "String",required = true),
            @ApiImplicitParam(value = "icon",name = "imgsrc",type = "String",required = false),
    })
    @RequestMapping(value = "/addMenu",method = {RequestMethod.POST})
    @ResponseBody
    public ResultVO addMenu(String parentCode, String menuName, String router, String imgsrc){

        try {
            String rs = menuService.addMenu(parentCode,menuName,router,imgsrc);

            if(rs.equals("success")){
                return Backtrack.success(true);
            }

           return Backtrack.errot("操作失败");
        }catch (Exception e){
            e.printStackTrace();
            return Backtrack.errot("操作失败");
        }


    }


    @ApiOperation(value = "修改菜单")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "父菜单编码",name = "parentCode",type = "String",required = true),
            @ApiImplicitParam(value = "菜单名称",name = "menuName",type = "String",required = true),
            @ApiImplicitParam(value = "路由",name = "router",type = "String",required = true),
            @ApiImplicitParam(value = "icon",name = "imgsrc",type = "String",required = false),
    })
    @RequestMapping(value = "/updateMenu",method = {RequestMethod.POST})
    @ResponseBody
    public ResultVO updateMenu(String menuCode, String menuName, String router, String imgsrc){

        try {
            Integer rs = menuService.updateMenu(menuCode,menuName,router,imgsrc);
            if(rs>0){
                return Backtrack.success(true);
            }
            return Backtrack.errot("操作失败");
        }catch (Exception e){
            e.printStackTrace();
            return Backtrack.errot("操作失败");
        }
    }

    @ApiOperation(value = "删除菜单")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "菜单编码",name = "menuCode",type = "String",required = true),
    })
    @RequestMapping(value = "/deleteMenu",method = {RequestMethod.POST})
    @ResponseBody
    public ResultVO deleteMenu(String menuCode){

        try {
            Integer rs = menuService.deleteMenu(menuCode);
            if(rs>0){
                return Backtrack.success(true);
            }
            return Backtrack.errot("请先解除绑定");
        }catch (Exception e){
            e.printStackTrace();
            return Backtrack.errot("操作失败");
        }
    }

    /**
     * 分页获取菜单
     * @param currentPage 页码‘
     * @return
     */
    @ApiOperation(value = "分页获取菜单")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "页码",name = "currentPage",type = "Integer",required = true),
    })
    @RequestMapping(value = "/getMenuMsgs",method = {RequestMethod.POST})
    @ResponseBody
    public ResultVO<PageData<Menu>> getMenuMsgs(Integer currentPage){

        PageData<Menu> list = menuService.getMenuMsgs(currentPage);

        if(null==list){
            return Backtrack.errot("操作失败");
        }

        return Backtrack.success(list,"操作成功");

    }

}
