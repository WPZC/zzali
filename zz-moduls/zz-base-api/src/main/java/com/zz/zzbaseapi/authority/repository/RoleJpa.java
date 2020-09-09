package com.zz.zzbaseapi.authority.repository;


import com.zz.jpatemplate.dao.BaseDao;
import com.zz.region.domain.authority.Role;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author wqy
 * @version 1.0
 * @date 2020/5/28 10:57
 */
public interface RoleJpa extends BaseDao<Role, Integer> {

    @Query(value = "select d from Role d where d.id=?1")
    Role selectById(Long id);

    @Query(nativeQuery = true,value = "SELECT * FROM role WHERE id IN(SELECT r_id FROM user_role WHERE u_id = ?1)")
    List<Role> findByUserRole(Long id);

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
    @Query(value = "delete from Role r where r.id=?1")
    int deleteByRId(Long rId);

}
