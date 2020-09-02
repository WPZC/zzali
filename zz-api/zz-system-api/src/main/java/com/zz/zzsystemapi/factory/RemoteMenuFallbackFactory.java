package com.zz.zzsystemapi.factory;

import com.zz.region.domain.PageData;
import com.zz.region.domain.authority.Menu;
import com.zz.region.vo.ResultVO;
import com.zz.zzsystemapi.service.MenuMangementFegin;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 用户服务降级处理
 * 
 * @author ruoyi
 */
@Component
public class RemoteMenuFallbackFactory implements FallbackFactory<MenuMangementFegin>
{
    @Override
    public MenuMangementFegin create(Throwable throwable)
    {
        return new MenuMangementFegin()
        {


            @Override
            public ResultVO<String> addMenu(Menu menu) {
                return null;
            }

            @Override
            public ResultVO<String> findByMenuMaxMenuCode(String menuCode) {
                return null;
            }

            @Override
            public ResultVO<Integer> updateMenu(String menuCode, String menuName, String router, String imgsrc) {
                return null;
            }

            @Override
            public ResultVO<Boolean> findByMenuIsBind(String menuCode) {
                return null;
            }

            @Override
            public ResultVO<Integer> deleteMenu(String menuCode) {
                return null;
            }

            @Override
            public ResultVO<List<Menu>> getMenus() {
                return null;
            }

            @Override
            public ResultVO<PageData<Menu>> getMenuMsgs(Integer currentPage) {
                return null;
            }
        };
    }
}
