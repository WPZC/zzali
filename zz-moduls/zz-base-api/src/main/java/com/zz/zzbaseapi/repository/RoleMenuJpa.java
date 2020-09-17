package com.zz.zzbaseapi.repository;


import com.zz.domain.authority.RoleMenu;
import com.zz.jpatemplate.dao.BaseDao;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author wqy
 * @version 1.0
 * @date 2020/6/12 16:51
 */
public interface RoleMenuJpa extends BaseDao<RoleMenu,Integer> {

    @Query(nativeQuery = true,value = "SELECT * FROM role_menu WHERE r_id=?1")
    List<RoleMenu> findByRId(Long rId);

}
