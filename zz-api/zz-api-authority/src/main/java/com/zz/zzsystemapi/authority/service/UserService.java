package com.zz.zzsystemapi.authority.service;

import com.zz.region.domain.PageData;
import com.zz.zzsystemapi.commondb.entity.RoleEntity;
import com.zz.zzsystemapi.commondb.entity.UserEntity;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author wqy
 * @version 1.0
 * @date 2020/5/28 11:16
 */
public interface UserService{

    /**
     * 根据用户名查询
     * @param username
     * @return
     */
    UserEntity findByUserName(String username);

    /**
     * 保存用户
     * @param userEntity
     * @return
     */
    String addUser(@RequestBody UserEntity userEntity, Long roleId);

    /**
     * 根据用户id查询对象
     * @param id
     * @return
     */
    UserEntity findById(Long id);

    /**
     * 编辑用户
     * @param userEntity
     * @return
     */
    String updateUser(UserEntity userEntity,Long roleId);

    /**
     * 获取用户列表(分页)
     * @param currentPage
     * @return
     */
    PageData<UserEntity> findByPage(Integer currentPage);

    /**
     * 根据id删除用户
     * @param id
     * @return
     */
    String deleteByuId(Long id);

    /**
     * 根据用户id查找角色id
     * @param id
     * @return
     */
    RoleEntity oleRole(Long id);
}
