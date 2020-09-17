package com.zz.vo.view;

import com.zz.domain.authority.Menu;
import lombok.Data;

import java.util.List;

/**
 * @author wqy
 * @version 1.0
 * @date 2020/6/15 11:35
 */
@Data
public class RoleMenuView {

    //标题
    private String title;
    //图标路劲
    private String imgSrc;
    //子菜单
    private List<Menu> menus;
    //菜单序号
    private String index;
    //是否有子集
    private Boolean isSsons = false;
    //路由
    private String router;
}
