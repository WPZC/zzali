package com.zz.zzbaseapi.base.service.impl;

import com.alibaba.fastjson.JSON;
import com.zz.KafkaProducer;
import com.zz.domain.PageData;
import com.zz.domain.authority.Role;
import com.zz.domain.authority.User;
import com.zz.domain.authority.UserRole;
import com.zz.jpatemplate.dao.BaseDao;
import com.zz.jpatemplate.service.BaseService;
import com.zz.security.utils.SecurityUtils;
import com.zz.vo.SendData;
import com.zz.zzbaseapi.domain.log.LogInfo;
import com.zz.zzbaseapi.repository.RoleJpa;
import com.zz.zzbaseapi.repository.UserJpa;
import com.zz.zzbaseapi.repository.UserRoleJpa;
import com.zz.zzbaseapi.base.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户实现
 */
@Service
public class UserServiceImpl extends BaseService<User,UserJpa> implements UserService {

    @Autowired
    private UserJpa userJpa;
    @Autowired
    private UserRoleJpa userRoleJpa;
    @Autowired
    private RoleJpa roleJpa;
    @Autowired
    private KafkaProducer kafkaProducer;

    /**
     * 注入仓库位置
     * {@link BaseDao}
     *
     * @param userJpa
     */
    public UserServiceImpl(UserJpa userJpa) {
        super(userJpa);
    }

    @Override
    public String addUser(User userEntity, Long roleId) {

        //根据用户名查询是用户是否存在。
        //存在报用户名重复提示。不存在保存用户
        User old =  userJpa.findByUsername(userEntity.getUsername());
        if(old != null){
            return "用户名已经存在！";
        }
        User user = userJpa.save(userEntity);
        UserRole userRoleEntity=new UserRole();
        userRoleEntity.setRId(roleId);
        userRoleEntity.setUId(user.getId());
        UserRole userRole=userRoleJpa.save(userRoleEntity);
        if(user!=null && userRole!=null){
            return "操作成功";
        }
        return "操作失败";
    }

    @Override
    public String updateUser(User userEntity, Long roleId) {

        /**
         * 根据id查询用户是否存在
         */
        User old = userJpa.findByUserId(userEntity.getId());

        if (old == null) {
            return "用户不存在";
        }

        //判断该对象要更新的用户名是否一致？
        //一致直接更新，否判断要更新的用户名在库中是否存在？
        //存在提示用户名已存在，否直接更新。
        if(!old.getUsername().equals(userEntity.getUsername())){
            User user =  userJpa.findByUsername(userEntity.getUsername());
            if(user != null){
                return "用户名已经存在！";
            }
        }

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
        UserRole userRole=userRoleJpa.findByuId(userEntity.getId());
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
            return "操作成功";
        }
        return "操作失败";

    }

    @Override
    public PageData<User> findByPage(Integer currentPage) {
        return findPages(currentPage,5);
    }

    @Override
    public String deleteByuId(Long id) {

        //根据用户id删除关联的用户角色表中的数据
        UserRole userRole=userRoleJpa.findByuId(id);

        if(userRole!=null){
            userRoleJpa.deleteByUserId(id);
        }
        //删除该用户
        userJpa.deleteByuId(id);
        return "操作成功";
    }

    @Override
    public Role oleRole(Long id) {

        UserRole userRole=userRoleJpa.findByuId(id);
        if(userRole!=null){
            Role role = roleJpa.selectById(userRole.getRId());
            if(role !=null){
                return role;
            }else{
                return null;
            }
        }
        return null;
    }

    @Override
    public User getUser(String username) {

        User entity = userJpa.findByUsername(username);

        entity.setPassword(null);

        return entity;
    }

    @Override
    public User findByUserName(String username) {

        Thread t = Thread.currentThread();
        StackTraceElement[] s = t.getStackTrace();

        kafkaProducer.sendMsg(new SendData<>("topic.user",new LogInfo(username)));
        return userJpa.findByUsername(username);
    }


}
