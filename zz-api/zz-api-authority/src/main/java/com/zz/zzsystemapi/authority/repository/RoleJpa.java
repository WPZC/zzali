package com.zz.zzsystemapi.authority.repository;


import com.zz.jpatemplate.dao.BaseDao;
import com.zz.zzsystemapi.commondb.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author wqy
 * @version 1.0
 * @date 2020/5/28 10:57
 */
public interface RoleJpa extends BaseDao<RoleEntity, Integer> {

    @Query(value = "select d from RoleEntity d where d.id=?1")
    RoleEntity selectById(Long id);

    @Query(nativeQuery = true,value = "SELECT * FROM role WHERE id IN(SELECT r_id FROM user_role WHERE u_id = ?1)")
    List<RoleEntity> findByUserRole(Long id);

    /**
     * 查询该角色是否被绑定
     * @param rId
     * @return
     */
    @Query(nativeQuery = true,value = "SELECT COUNT(*) FROM `user_role` where r_id = ?1")
    int findByUserRoleBind(Long rId);

    /**
     * 根据Id删除
     * @param rId
     * @return
     */
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "delete from RoleEntity r where r.id=?1")
    int deleteByRId(Long rId);

}
