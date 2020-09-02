package com.zz.zzjwt.controller.base.service.impl;

import com.zz.region.domain.PageData;
import com.zz.region.domain.authority.Menu;
import com.zz.region.methods.Backtrack;
import com.zz.region.methods.utils.Utils;
import com.zz.zzjwt.controller.base.service.MenuService;
import com.zz.zzsystemapi.service.MenuMangementFegin;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 菜单模块
 * @author wqy
 * @version 1.0
 * @date 2020/6/9 13:45
 */
@Service
public class MenuServiceImpl implements MenuService {

    @Resource
    private MenuMangementFegin menuMangementFegin;

    @Override
    public String addMenu(String p_code,String menu_name,String router,String imgsrc) {

        //查询出当前菜单下最大的menu_code
        //Backtrack.check校验
        String nowCode = Backtrack.checkData(menuMangementFegin.findByMenuMaxMenuCode(p_code));
        String menuCode = "";
        if(Utils.isNull(nowCode)){
            //直接赋值0001
            menuCode = p_code+"0001";
        }else{
            //在当前菜单最大menu_code+1
            menuCode = (Long.parseLong(nowCode)+1)+"";
        }

        //存入数据库
        Menu menu = Menu.builder()
                .menuCode(menuCode)
                .menuName(menu_name)
                .imgsrc(imgsrc)
                .router(router)
                .build();
        String rs = Backtrack.checkMsg(menuMangementFegin.addMenu(menu));

        return rs;
    }

    @Override
    public Integer updateMenu(String menuCode, String menuName,String router,String imgsrc) {

        Integer rs = Backtrack.checkData(menuMangementFegin.updateMenu(menuCode,menuName,router,imgsrc));

        return rs;

    }

    @Override
    public Integer deleteMenu(String menuCode) {

        //查询该菜单以及子菜单是否被角色绑定，绑定则不删除
        Boolean isBind = Backtrack.checkData(menuMangementFegin.findByMenuIsBind(menuCode));
        //如果为true则可以删除
        if(isBind){
            Integer rs = Backtrack.checkData(menuMangementFegin.deleteMenu(menuCode));
            return rs;
        }
        return 0;
    }

    @Override
    public PageData<Menu> getMenuMsgs(Integer currentPage) {

        PageData<Menu> list = Backtrack.checkData(menuMangementFegin.getMenuMsgs(currentPage));

        return list;
    }
}
