package com.zz.zzbaseapi.controller.base.service;

import com.zz.region.domain.PageData;
import com.zz.region.domain.authority.Menu;

/**
 * @author wqy
 * @version 1.0
 * @date 2020/6/9 13:44
 */
public interface MenuService {

    /**
     * 添加菜单
     * @param p_code 父code
     * @param menu_name 菜单名称
     * @return
     */
    String addMenu(String p_code,String menu_name,String router,String imgsrc);

    /**
     * 修改菜单
     * @param menuCode
     * @param menuName
     * @return
     */
    Integer updateMenu(String menuCode, String menuName,String router,String imgsrc);

    /**
     * 删除菜单
     * @param menuCode
     * @return
     */
    Integer deleteMenu(String menuCode);


    /**
     * 分页获取菜单
     * @param currentPage
     * @return
     */
    PageData<Menu> getMenuMsgs(Integer currentPage);
}
