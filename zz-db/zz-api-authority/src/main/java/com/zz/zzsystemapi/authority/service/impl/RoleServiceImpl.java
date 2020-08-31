package com.zz.zzsystemapi.authority.service.impl;

import com.zz.jpatemplate.service.BaseService;
import com.zz.region.domain.PageData;
import com.zz.zzsystemapi.authority.service.RoleService;
import com.zz.zzsystemapi.commondb.entity.Menu;
import com.zz.zzsystemapi.commondb.entity.RoleEntity;
import com.zz.zzsystemapi.commondb.entity.RoleMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import com.zz.zzsystemapi.authority.repository.MenuJpa;
import com.zz.zzsystemapi.authority.repository.RoleJpa;
import com.zz.zzsystemapi.authority.repository.RoleMenuJpa;

import java.util.List;

/**
 * @author wqy
 * @version 1.0
 * @date 2020/5/28 11:26
 */
@Service
public class RoleServiceImpl extends BaseService<RoleEntity, RoleJpa> implements RoleService {

    @Autowired
    private RoleJpa roleJpa;
    @Autowired
    private MenuJpa menuJpa;

    @Autowired
    private RoleMenuJpa roleMenuJpa;

    public RoleServiceImpl(RoleJpa roleJpa) {
        super(roleJpa);
    }

    @Override
    public List<RoleEntity> findByUserRole(Long id) {
        return roleJpa.findByUserRole(id);
    }

    @Override
    public RoleEntity addRole(String name, String description) {

        RoleEntity roleEntity = new RoleEntity();

        roleEntity.setName(name);
        roleEntity.setDescription(description);

        return roleJpa.save(roleEntity);
    }

    @Override
    public int roleBindMenu(Long rId, List<Long> mIds) {
        return 0;
    }

    @Override
    public String deleteByRId(Long rId) {

        //查询改角色是否有用户绑定,如果有绑定的则不删除并返回错误信息
        int binds = roleJpa.findByUserRoleBind(rId);
        if(binds>0){
            return "请先接触用户与该角色的绑定";
        }
        //删除该角色
        roleJpa.deleteByRId(rId);

        return "删除成功";
    }

    @Override
    public RoleEntity updateRole(RoleEntity roleEntity) {
        return roleJpa.save(roleEntity);
    }

    @Override
    public int updateRoleBindMenu(Long rId, List<Long> mIds) {
        return 0;
    }

    @Override
    public PageData<RoleEntity> getRoleMsgs(Integer currentPage) {

        return findPages(currentPage,5);
    }

    @Override
    public List<RoleMenu> getRoleMenus(Long rId) {

        return roleMenuJpa.findByRId(rId);
    }

    @Override
    public String saveNodes(List<RoleMenu> roleMenus) {

        try {

            List<RoleMenu> menus = roleMenuJpa.findByRId(roleMenus.get(0).getRId());
            //判断是否已存在菜单，存在则删除
            if(null==menus||menus.size()<=0){
                roleMenuJpa.saveAll(roleMenus);
            }else{
                //删除菜单
                roleMenuJpa.deleteAll(menus);
                //保存菜单
                roleMenuJpa.saveAll(roleMenus);
            }
        }catch (Exception e){
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return "操作成功";
    }

    @Override
    public List<RoleEntity> getRoleMsgsList() {
        return roleJpa.findAll();
    }

    @Override
    public List<Menu> getMenuByRId(Long id) {
        return menuJpa.getMenuByRId(id);
    }
}
