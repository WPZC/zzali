package com.zz.zzsystemapi.factory;

import com.zz.domain.authority.Role;
import com.zz.zzsystemapi.service.RoleMangementFegin;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

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
            public List<Role> findByUserRole(Long id, String encode) {
                return null;
            }
        };
    }
}
