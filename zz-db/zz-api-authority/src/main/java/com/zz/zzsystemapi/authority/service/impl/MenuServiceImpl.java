package com.zz.zzsystemapi.authority.service.impl;

import com.zz.jpatemplate.service.BaseService;
import com.zz.region.domain.PageData;
import com.zz.zzsystemapi.authority.service.MenuService;
import com.zz.zzsystemapi.commondb.entity.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zz.zzsystemapi.authority.repository.MenuJpa;

import java.util.List;

/**
 * 菜单业务
 * @author wqy
 * @version 1.0
 * @date 2020/6/9 14:46
 */
@Service
public class MenuServiceImpl extends BaseService<Menu, MenuJpa> implements MenuService {

    @Autowired
    private MenuJpa menuJpa;

    public MenuServiceImpl(MenuJpa menuJpa) {
        super(menuJpa);
    }

    @Override
    public String addMenu(Menu menu) {

        Menu m = menuJpa.save(menu);

        if(m!=null){
            return "success";
        }

        return null;
    }

    @Override
    public String findByMenuMaxMenuCode(String menuCode) {

        String code = menuJpa.findByMenuCodeMaxLen(menuCode+"%",menuCode.length()+4);

        return code;
    }

    @Override
    public Integer updateMenu(String menuCode,String menuName,String router,String imgsrc) {

        int rs = menuJpa.updateMenuByMenuCode(menuName,menuCode,router,imgsrc);

        return rs;
    }

    @Override
    public Integer deleteMenu(String menuCode) {

        int rs = menuJpa.deleteByMenuCode(menuCode+"%");

        return rs;
    }

    @Override
    public Boolean findByMenuIsBind(String menuCode) {

        //查询当前menuCode和子集的ID
        int count = menuJpa.findCountByMenuCode(menuCode+"%");

        //如果count>0则，有角色绑定，不能删除
        if(count>0){
            return false;
        }

        return true;
    }

    @Override
    public List<Menu> getMenus() {
        return menuJpa.findAll();
    }

    @Override
    public PageData<Menu> getMenuMsgs(Integer currentPage) {
        return findPages(currentPage,5);
    }
}
