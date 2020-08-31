package com.zz.zzsystemapi.authority.service;

import com.zz.region.domain.PageData;
import com.zz.zzsystemapi.commondb.entity.Menu;

import java.util.List;

/**
 * @author wqy
 * @version 1.0
 * @date 2020/6/9 14:46
 */
public interface MenuService {

    /**
     * 添加菜单
     * @param menu
     * @return
     */
    String addMenu(Menu menu);

    /**
     * 查询当前菜单的最大menu_code
     * @param menuCode
     * @return
     */
    String findByMenuMaxMenuCode(String menuCode);

    /**
     * 修改菜单
     * @param menuCode
     * @param menuName
     * @return
     */
    Integer updateMenu(String menuCode,String menuName,String router,String imgsrc);

    /**
     * 删除菜单
     * @param menuCode
     * @return
     */
    Integer deleteMenu(String menuCode);

    /**
     * 查询菜单是否被绑定
     * @param menuCode
     * @return
     */
    Boolean findByMenuIsBind(String menuCode);

    /**
     * 获取所有菜单
     * @return
     */
    List<Menu> getMenus();

    /**
     * 分页获取菜单
     * @param currentPage
     * @return
     */
    PageData<Menu> getMenuMsgs(Integer currentPage);
}
