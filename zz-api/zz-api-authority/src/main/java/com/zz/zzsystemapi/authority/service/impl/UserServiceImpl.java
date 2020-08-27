package com.zz.zzsystemapi.authority.service.impl;
import com.zz.jpatemplate.service.BaseService;
import com.zz.region.domain.PageData;
import com.zz.zzsystemapi.authority.service.UserService;
import com.zz.zzsystemapi.commondb.entity.RoleEntity;
import com.zz.zzsystemapi.commondb.entity.UserEntity;
import com.zz.zzsystemapi.commondb.entity.UserRoleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zz.zzsystemapi.authority.repository.RoleJpa;
import com.zz.zzsystemapi.authority.repository.UserJpa;
import com.zz.zzsystemapi.authority.repository.UserRoleJpa;

/**
 * @author wqy
 * @version 1.0
 * @date 2020/5/28 11:16
 */
@Service
public class UserServiceImpl extends BaseService<UserEntity, UserJpa> implements UserService {

    @Autowired
    private UserJpa userJpa;

    @Autowired
    private UserRoleJpa userRoleJpa;

    @Autowired
    private RoleJpa roleJpa;

    public UserServiceImpl(UserJpa userJpa) {
        super(userJpa);
    }

    @Override
    public UserEntity findByUserName(String username) {
        return userJpa.findByUsername(username);
    }

    @Override
    public String addUser(UserEntity userEntity,Long roleId) {
        UserEntity user = userJpa.save(userEntity);
        UserRoleEntity userRoleEntity=new UserRoleEntity();
        userRoleEntity.setRId(roleId);
        userRoleEntity.setUId(user.getId());
        UserRoleEntity userRole=userRoleJpa.save(userRoleEntity);
        if(user!=null && userRole!=null){
            return "success";
        }
        return null;
    }

    @Override
    public UserEntity findById(Long id) {

        return userJpa.findByUserId(id);
    }

    @Override
    public String updateUser(UserEntity userEntity,Long roleId) {
        //更新用户信息
        int user = userJpa.updateUser(
                userEntity.getId(),
                userEntity.getUsername(),
                userEntity.getPassword(),
                userEntity.getIphone(),
                userEntity.getSex(),
                userEntity.getOrganizationName(),
                userEntity.getOrganizationNum()
                );

       //根据用户的id查找角色关联表中的信息
        UserRoleEntity userRole=userRoleJpa.findByuId(userEntity.getId());
        int role=0;
        if(userRole!=null){
           if(!userRole.getRId().equals(roleId)){
               role=  userRoleJpa.updateUserRole(
                       roleId,
                       userRole.getId()
               );
           }
        }
        if(user>0 && role>0){
            return "success";
        }
        return null;
    }

    @Override
    public PageData<UserEntity> findByPage(Integer currentPage) {
        return findPages(currentPage,5);
    }

    @Override
    public String deleteByuId(Long id) {
        //根据用户id删除关联的用户角色表中的数据
        UserRoleEntity userRole=userRoleJpa.findByuId(id);

        if(userRole!=null){
            userRoleJpa.deleteByUserId(id);
        }
        //删除该用户
        userJpa.deleteByuId(id);

        return "删除成功";
    }

    @Override
    public RoleEntity oleRole(Long id) {
        UserRoleEntity userRole=userRoleJpa.findByuId(id);
        if(userRole!=null){
            RoleEntity roleEntity = roleJpa.selectById(userRole.getRId());
            if(roleEntity!=null){
                return roleEntity;
            }else{
                return null;
            }
        }
        return null;
    }

}
