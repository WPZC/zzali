package com.zz.zzbaseapi.repository;


import com.zz.domain.authority.Menu;
import com.zz.jpatemplate.dao.BaseDao;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 菜单查询
 * @author wqy
 * @version 1.0
 * @date 2020/6/9 14:45
 */
public interface MenuJpa extends BaseDao<Menu,Integer> {

    /**
     * 查询菜单最大code
     * @param menuCode
     * @param len
     * @return
     */
    @Query(nativeQuery = true,value = "SELECT MAX(menu_code) FROM `menu` WHERE menu_code LIKE ?1 AND CHAR_LENGTH(menu_code) = ?2")
    String findByMenuCodeMaxLen(String menuCode,Integer len);

    /**
     * 根据菜单编号修改
     * @param menuName
     * @param menuCode
     * @return
     */
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update Menu m set m.menuName = ?1,m.router = ?3,m.imgsrc = ?4 where m.menuCode = ?2")
    int updateMenuByMenuCode(String menuName, String menuCode,String router,String imgsrc);

    /**
     * 查询该menuCode及以下的menuCode是否被绑
     * @param menuCode
     * @return
     */
    @Query(nativeQuery = true,value = "SELECT count(*) FROM role_menu WHERE m_id IN (SELECT id FROM menu WHERE menu_code LIKE ?1)")
    int findCountByMenuCode(String menuCode);

    /**
     * 删除菜单
     * @param menuCode
     * @return
     */
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(nativeQuery = true,value = "DELETE FROM menu where menu_code LIKE ?1")
    int deleteByMenuCode(String menuCode);

    /**
     * 根据用户ID获取对应绑定菜单信息
     * @param id
     * @return
     */
    @Query(nativeQuery = true,value = "SELECT * FROM menu WHERE id IN(SELECT m_id FROM role_menu WHERE r_id = (SELECT r_id from user_role WHERE u_id = ?1))")
    List<Menu> getMenuByRId(Long id);
}
