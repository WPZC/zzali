package com.zz.zzsystemapi.authority.repository;


import com.zz.jpatemplate.dao.BaseDao;
import com.zz.zzsystemapi.commondb.entity.UserRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author wqy
 * @version 1.0
 * @date 2020/5/28 10:56
 */
public interface UserRoleJpa extends BaseDao<UserRoleEntity, Integer> {

    /**
     * 根据用户Id删除对象
     * @param uId
     * @return
     */
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "delete from UserRoleEntity ur where ur.uId=?1")
    int deleteByUserId(Long uId);

    /**
     *根据用户id查找对象
     * @param uId
     * @return
     */
    @Query(value = "select user from UserRoleEntity user where user.uId=?1")
    UserRoleEntity findByuId(Long uId);

    /**
     * 更新角色
     * @param
     */
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update UserRoleEntity user set user.rId = ?1  where user.id = ?2")
    int updateUserRole(Long rId , Long id);
}
