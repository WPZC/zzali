package com.zz.zzsystemapi.authority.service;

import com.zz.region.domain.PageData;
import com.zz.zzsystemapi.commondb.entity.Menu;
import com.zz.zzsystemapi.commondb.entity.RoleEntity;
import com.zz.zzsystemapi.commondb.entity.RoleMenu;

import java.util.List;

/**
 * @author wqy
 * @version 1.0
 * @date 2020/5/28 11:26
 */
public interface RoleService {

    /**
     * 根据用户ID查询角色信息
     * @param id
     * @return
     */
    List<RoleEntity> findByUserRole(Long id);


    /**
     * 添加角色
     * @param name
     * @param description
     * @return
     */
    RoleEntity addRole(String name,String description);

    /**
     * 给角色绑定菜单
     * @param rId
     * @param mIds
     * @return
     */
    int roleBindMenu(Long rId, List<Long> mIds);

    /**
     * 删除角色
     * @param rId
     * @return
     */
    String deleteByRId(Long rId);

    /**
     * 修改角色
     * @param roleEntity
     * @return
     */
    RoleEntity updateRole(RoleEntity roleEntity);

    /**
     * 修改菜单绑定信息
     * @param rId
     * @param mIds
     * @return
     */
    int updateRoleBindMenu(Long rId, List<Long> mIds);

    /**
     * 分页查询
     * @param currentPage
     * @return
     */
    PageData<RoleEntity> getRoleMsgs(Integer currentPage);

    /**
     * 获取角色菜单
     * @param uId
     * @return
     */
    List<RoleMenu> getRoleMenus(Long uId);

    /**
     * 给角色绑定菜单
     * @param roleMenus
     * @return
     */
    String saveNodes(List<RoleMenu> roleMenus);

    /**
     * 查询角色列表List
     * @return
     */
    List<RoleEntity> getRoleMsgsList();

    /**
     * 根据用户ID获取对应绑定菜单信息
     * @param id
     * @return
     */
    List<Menu> getMenuByRId(Long id);
}
