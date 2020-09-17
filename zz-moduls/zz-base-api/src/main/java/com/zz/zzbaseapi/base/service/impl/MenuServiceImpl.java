package com.zz.zzbaseapi.base.service.impl;

import com.zz.domain.PageData;
import com.zz.domain.authority.Menu;
import com.zz.jpatemplate.dao.BaseDao;
import com.zz.jpatemplate.service.BaseService;
import com.zz.region.methods.utils.StringUtil;
import com.zz.region.methods.utils.Utils;
import com.zz.zzbaseapi.repository.MenuJpa;
import com.zz.zzbaseapi.base.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 菜单模块
 * @author wqy
 * @version 1.0
 * @date 2020/6/9 13:45
 */
@Service
public class MenuServiceImpl extends BaseService<Menu, MenuJpa> implements MenuService {

    @Autowired
    private MenuJpa menuJpa;

    /**
     * 注入仓库位置
     * {@link BaseDao}
     *
     * @param menuJpa
     */
    public MenuServiceImpl(MenuJpa menuJpa) {
        super(menuJpa);
    }

    @Override
    public String addMenu(String p_code,String menu_name,String router,String imgsrc) {

        //查询出当前菜单下最大的menu_code
        //Backtrack.check校验
        String nowCode = menuJpa.findByMenuCodeMaxLen(p_code+"%",p_code.length()+4);
        String menuCode = "";
        if(Utils.isNull(nowCode)){
            //直接赋值0001
            menuCode = p_code+"0001";
        }else{
            //在当前菜单最大menu_code+1
            menuCode = (Long.parseLong(nowCode)+1)+"";
        }

        //存入数据库
        Menu menu = new Menu();
        menu.setMenuCode(menuCode);
        menu.setImgsrc(imgsrc);
        menu.setMenuName(menu_name);
        menu.setRouter(router);
        Menu rs = menuJpa.save(menu);

        if(StringUtil.isNullOrEmpty(rs)){
            return "操作失败";
        }
        return "操作成功";
    }

    @Override
    public Integer updateMenu(String menuCode, String menuName,String router,String imgsrc) {

        Integer rs = menuJpa.updateMenuByMenuCode(menuName,menuCode,router,imgsrc);

        return rs;

    }

    @Override
    public Integer deleteMenu(String menuCode) {

        //查询该菜单以及子菜单是否被角色绑定，绑定则不删除
        //查询当前menuCode和子集的ID
        //如果count>0则，有角色绑定，不能删除

        int count = menuJpa.findCountByMenuCode(menuCode+"%");
        //如果为true则可以删除
        if(!(count>0)){
            Integer rs = menuJpa.deleteByMenuCode(menuCode);
            return rs;
        }
        return 0;
    }

    @Override
    public PageData<Menu> getMenuMsgs(Integer currentPage) {

        PageData<Menu> list = findPages(currentPage,5);

        return list;
    }
}
