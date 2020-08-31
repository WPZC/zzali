package com.zz.zzsystemapi.factory;

import com.zz.region.domain.authority.RoleEntity;
import com.zz.region.domain.authority.UserEntity;
import com.zz.region.vo.ResultVO;
import com.zz.zzsystemapi.service.RoleMangementFegin;
import com.zz.zzsystemapi.service.UserMangementFegin;
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
            public ResultVO<List<RoleEntity>> getRoleMsgsList() {
                return null;
            }
        };
    }
}
