package com.zz.zzjwt.controller.base.service.impl;

import com.zz.region.domain.PageData;
import com.zz.region.domain.authority.RoleEntity;
import com.zz.region.domain.authority.UserEntity;
import com.zz.region.methods.Backtrack;
import com.zz.zzjwt.controller.base.service.UserService;
import com.zz.zzsystemapi.service.UserMangementFegin;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 用户实现
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMangementFegin userMangementFegin;
    @Override
    public String addUser(UserEntity userEntity, Long roleId) {
        return Backtrack.checkMsg(userMangementFegin.addUser(userEntity, roleId));
    }

    @Override
    public String updateUser(UserEntity userEntity, Long roleId) {
        return Backtrack.checkMsg(userMangementFegin.updateUser(userEntity, roleId));
    }

    @Override
    public PageData<UserEntity> findByPage(Integer currentPage) {
        PageData<UserEntity> list = Backtrack.checkData(userMangementFegin.findByPage(currentPage));
        return list;
    }

    @Override
    public String deleteByuId(Long id) {
        return Backtrack.checkMsg(userMangementFegin.deleteByuId(id));
    }

    @Override
    public RoleEntity oleRole(Long id) {
        return userMangementFegin.oleRole(id);
    }

    @Override
    public UserEntity getUser(String username) {

        UserEntity entity = userMangementFegin.findByUserName(username);

        entity.setPassword(null);

        return entity;
    }


}
