package com.zz.zzsystemapi.authority.globalregister;

import com.zz.zzsystemapi.authority.service.RoleService;
import com.zz.zzsystemapi.authority.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.zz.zzsystemapi.authority.service.MenuService;

/**
 * 业务注册
 * @author wqy
 * @version 1.0
 * @date 2020/5/28 11:13
 */
@Component
public class GlobalController {

    /**
     * 用户业务
     */
    @Autowired
    public UserService userService;

    /**
     * 角色
     */
    @Autowired
    public RoleService roleService;

    /**
     * 菜单
     */
    @Autowired
    public MenuService menuService;

}
