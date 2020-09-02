package com.zz.zzsystemapi.factory;

import com.zz.region.domain.PageData;
import com.zz.region.domain.authority.Menu;
import com.zz.region.domain.authority.RoleEntity;
import com.zz.region.domain.authority.RoleMenu;
import com.zz.region.vo.ResultVO;
import com.zz.zzsystemapi.service.RoleMangementFegin;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 用户服务降级处理
 * 
 * @author ruoyi
 */
@Component
public class RemoteRoleFallbackFactory implements FallbackFactory<RoleMangementFegin>
{
    @Override
    public RoleMangementFegin create(Throwable throwable)
    {
        return new RoleMangementFegin()
        {

            @Override
            public List<RoleEntity> findByUserRole(Long id) {
                return null;
            }

            @Override
            public ResultVO<RoleEntity> addRole(String name, String description) {
                return null;
            }

            @Override
            public ResultVO<String> deleteByRId(Long rId) {
                return null;
            }

            @Override
            public ResultVO<RoleEntity> updateRole(RoleEntity roleEntity) {
                return null;
            }

            @Override
            public ResultVO<PageData<RoleEntity>> getRoleMsgs(Integer currentPage) {
                return null;
            }

            @Override
            public ResultVO<List<RoleMenu>> getRoleMenus(Long r_id) {
                return null;
            }

            @Override
            public ResultVO<String> saveNodes(List<RoleMenu> roleMenus) {
                return null;
            }

            @Override
            public ResultVO<List<RoleEntity>> getRoleMsgsList() {
                return null;
            }

            @Override
            public ResultVO<List<Menu>> getMenuByRId(Long id) {
                return null;
            }
        };
    }
}
