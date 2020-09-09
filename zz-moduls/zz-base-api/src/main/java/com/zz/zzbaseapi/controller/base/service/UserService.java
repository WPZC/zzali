package com.zz.zzbaseapi.controller.base.service;

import com.zz.region.domain.PageData;
import com.zz.region.domain.authority.Role;
import com.zz.region.domain.authority.User;

/**
 * 用户
 */
public interface UserService {

    /**
     * 添加用户
     * @return
     */
    String addUser(User user, Long roleId);

    /**
     * 更新用户
     * @return
     */
    String updateUser(User user, Long roleId);

    /**
     * 获取用户列表（分页）
     * @param currentPage
     * @return
     */
    PageData<User> findByPage(Integer currentPage);

    /**
     * 删除用户
     * @param id
     */
    String deleteByuId(Long id);

    /**
     * 根据用户id查询角色
     * @param id
     * @return
     */
    Role oleRole(Long id);

    /**
     * 获取用户信息
     * @param username
     * @return
     */
    User getUser(String username);

    /**
     * 根据用户名查询
     * @param username
     * @return
     */
    User findByUserName(String username);
}
