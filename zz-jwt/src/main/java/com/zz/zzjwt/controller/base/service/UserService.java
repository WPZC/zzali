package com.zz.zzjwt.controller.base.service;

import com.zz.region.domain.PageData;
import com.zz.region.domain.authority.RoleEntity;
import com.zz.region.domain.authority.UserEntity;

/**
 * 用户
 */
public interface UserService {

    /**
     * 添加用户
     * @return
     */
    String addUser(UserEntity userEntity, Long roleId);

    /**
     * 更新用户
     * @return
     */
    String updateUser(UserEntity userEntity, Long roleId);

    /**
     * 获取用户列表（分页）
     * @param currentPage
     * @return
     */
    PageData<UserEntity> findByPage(Integer currentPage);

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
    RoleEntity oleRole(Long id);

    /**
     * 获取用户信息
     * @param username
     * @return
     */
    UserEntity getUser(String username);
}
