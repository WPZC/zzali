package com.zz.zzbaseapi.repository;

import com.zz.domain.authority.UserRole;
import com.zz.jpatemplate.dao.BaseDao;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author wqy
 * @version 1.0
 * @date 2020/5/28 10:56
 */
public interface UserRoleJpa extends BaseDao<UserRole, Integer> {

    /**
     * 根据用户Id删除对象
     * @param uId
     * @return
     */
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "delete from UserRole ur where ur.uId=?1")
    int deleteByUserId(Long uId);

    /**
     *根据用户id查找对象
     * @param uId
     * @return
     */
    @Query(value = "select user from UserRole user where user.uId=?1")
    UserRole findByuId(Long uId);

    /**
     * 更新角色
     * @param
     */
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update UserRole user set user.rId = ?1  where user.id = ?2")
    int updateUserRole(Long rId , Long id);
}
