package com.zz.zzsystemapi.service;

import com.zz.region.ServiceNameConstants;
import com.zz.region.domain.PageData;
import com.zz.region.domain.authority.Menu;
import com.zz.region.vo.ResultVO;
import com.zz.zzsystemapi.factory.RemoteMenuFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 菜单fegin  （调用指定的数据源）
 * @author wqy
 * @version 1.0
 * @date 2020/6/9 16:31
 */
@FeignClient(contextId = "menuMangementFegin", value = ServiceNameConstants.SYSTEM_SERVICE,fallbackFactory = RemoteMenuFallbackFactory.class)
public interface MenuMangementFegin {

    @RequestMapping(value = "/mct/addMenu",method = {RequestMethod.POST})
    ResultVO<String> addMenu(@RequestBody Menu menu);

    /**
     * 查询当前菜单的最大menu_code
     * @param menuCode
     * @return
     */
    @RequestMapping(value = "/mct/findByMenuMaxMenuCode",method = {RequestMethod.POST})
    ResultVO<String> findByMenuMaxMenuCode(@RequestParam("menuCode") String menuCode);

    /**
     * 修改菜单
     * @return
     */
    @RequestMapping(value = "/mct/updateMenu",method = {RequestMethod.POST})
    ResultVO<Integer> updateMenu(@RequestParam("menuCode") String menuCode
            , @RequestParam("menuName") String menuName
            , @RequestParam("router") String router
            , @RequestParam("imgsrc") String imgsrc);

    /**
     * 查看该菜单以及子菜单是否被绑定
     * @param menuCode
     * @return
     */
    @RequestMapping(value = "/mct/findByMenuIsBind",method = {RequestMethod.POST})
    ResultVO<Boolean> findByMenuIsBind(@RequestParam("menuCode") String menuCode);

    /**
     * 删除菜单
     * @param menuCode
     * @return
     */
    @RequestMapping(value = "/mct/deleteMenu",method = {RequestMethod.POST})
    ResultVO<Integer> deleteMenu(@RequestParam("menuCode") String menuCode);

    /**
     * 获取所有菜单
     * @return
     */
    @RequestMapping(value = "/mct/getMenus",method = {RequestMethod.POST})
    ResultVO<List<Menu>> getMenus();

    /**
     * 分页获取菜单
     * @param currentPage
     * @return
     */
    @RequestMapping(value = "/mct/getMenuMsgs",method = {RequestMethod.POST})
    ResultVO<PageData<Menu>> getMenuMsgs(@RequestParam("currentPage") Integer currentPage);
}
