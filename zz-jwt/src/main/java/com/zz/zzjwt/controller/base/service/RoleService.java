package com.zz.zzjwt.controller.base.service;

import com.zz.region.domain.PageData;
import com.zz.region.domain.authority.RoleEntity;
import com.zz.region.domain.authority.UserEntity;
import com.zz.region.vo.view.RoleMenuView;
import com.zz.region.vo.view.TreeEntity;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 角色
 * @author wqy
 * @version 1.0
 * @date 2020/6/11 15:13
 */
public interface RoleService {

    /**
     * 添加角色
     * @param name
     * @param description
     * @return
     */
    RoleEntity addRole(String name, String description);

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
     * 获取校色信息
     * @param currentPage
     * @return
     */
    PageData<RoleEntity> getRoleMsgs(Integer currentPage);

    /**
     * 获取所有节点
     * @return
     */
    TreeEntity getNodes(Long rId);

    /**
     *
     * @param rId
     * @param menuIds
     * @return
     */
    String saveNodes(Long rId, List<String> menuIds);

    /**
     * 获取角色列表List
     * @return
     */
    List<RoleEntity> getRoleMsgsList();

    /**
     * 根据用户获取用户对应角色绑定的菜单
     *
     * @param request@return
     */
    List<RoleMenuView> getRoleMenuMsg(HttpServletRequest request);
}
