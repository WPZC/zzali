package com.zz.zzsystemapi.factory;

import com.zz.region.domain.PageData;
import com.zz.region.domain.authority.RoleEntity;
import com.zz.region.domain.authority.UserEntity;
import com.zz.region.vo.ResultVO;
import com.zz.zzsystemapi.service.UserMangementFegin;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * 用户服务降级处理
 * 
 * @author ruoyi
 */
@Component
public class RemoteUserFallbackFactory implements FallbackFactory<UserMangementFegin>
{
    @Override
    public UserMangementFegin create(Throwable throwable)
    {
        return new UserMangementFegin()
        {

            @Override
            public UserEntity findByUserName(String username) {
                return null;
            }

            @Override
            public ResultVO<String> addUser(UserEntity userEntity, Long roleId) {
                return null;
            }

            @Override
            public ResultVO<String> updateUser(UserEntity userEntity, Long roleId) {
                return null;
            }

            @Override
            public ResultVO<PageData<UserEntity>> findByPage(Integer currentPage) {
                return null;
            }

            @Override
            public ResultVO<String> deleteByuId(Long id) {
                return null;
            }

            @Override
            public RoleEntity oleRole(Long id) {
                return null;
            }
        };
    }
}
