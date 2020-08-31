package com.zz.zzsystemapi.authority.controller;

import com.zz.region.domain.PageData;
import com.zz.region.methods.Backtrack;
import com.zz.region.vo.ResultVO;
import com.zz.zzsystemapi.commondb.entity.Menu;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.zz.zzsystemapi.authority.globalregister.GlobalController;

import java.util.List;

/**
 * @author wqy
 * @version 1.0
 * @date 2020/6/9 15:34
 */
@Controller
@RequestMapping(value = "/mct")
public class MenuController extends GlobalController {

    @RequestMapping(value = "/addMenu",method = {RequestMethod.POST})
    @ResponseBody
    public ResultVO<String> addMenu(@RequestBody Menu menu){

        try {

            String rs = menuService.addMenu(menu);

            return Backtrack.success(rs);

        }catch (Exception e){
            e.printStackTrace();
            return Backtrack.errot("操作失败");
        }



    }

    /**
     * 查询当前菜单的最大menu_code
     * @param menuCode
     * @return
     */
    @RequestMapping(value = "/findByMenuMaxMenuCode",method = {RequestMethod.POST})
    @ResponseBody
    public ResultVO<String> findByMenuMaxMenuCode(@RequestParam("menuCode") String menuCode){

        try {

            String rs = menuService.findByMenuMaxMenuCode(menuCode);

            return Backtrack.success(rs,"操作成功");

        }catch (Exception e){
            e.printStackTrace();
            return Backtrack.errot("操作失败");
        }

    }


    /**
     * 修改菜单
     * @return
     */
    @RequestMapping(value = "/updateMenu",method = {RequestMethod.POST})
    @ResponseBody
    public ResultVO<Integer> updateMenu(@RequestParam("menuCode") String menuCode
            ,@RequestParam("menuName") String menuName
            ,@RequestParam("router") String router
            ,@RequestParam("imgsrc") String imgsrc){

        return Backtrack.success(menuService.updateMenu(menuCode,menuName,router,imgsrc));

    }

    /**
     * 查看该菜单以及子菜单是否被绑定
     * @param menuCode
     * @return
     */
    @RequestMapping(value = "/findByMenuIsBind",method = {RequestMethod.POST})
    @ResponseBody
    public ResultVO<Boolean> findByMenuIsBind(@RequestParam("menuCode") String menuCode){

        Boolean rs = menuService.findByMenuIsBind(menuCode);

        return Backtrack.success(rs);

    }


    /**
     * 删除菜单
     * @param menuCode
     * @return
     */
    @RequestMapping(value = "/deleteMenu",method = {RequestMethod.POST})
    @ResponseBody
    public ResultVO<Integer> deleteMenu(@RequestParam("menuCode") String menuCode){

        return Backtrack.success(menuService.deleteMenu(menuCode));

    }

    /**
     * 获取所有菜单
     * @return
     */
    @RequestMapping(value = "/getMenus",method = {RequestMethod.POST})
    @ResponseBody
    public ResultVO<List<Menu>> getMenus(){

        return Backtrack.success(menuService.getMenus());

    }

    /**
     * 分页获取菜单
     * @param currentPage
     * @return
     */
    @RequestMapping(value = "/getMenuMsgs",method = {RequestMethod.POST})
    @ResponseBody
    public ResultVO<PageData<Menu>> getMenuMsgs(@RequestParam("currentPage") Integer currentPage){
        return Backtrack.success(menuService.getMenuMsgs(currentPage));
    }




}
