package com.zz.zzsystemapi.factory;

import com.zz.domain.authority.Role;
import com.zz.domain.authority.User;
import com.zz.zzsystemapi.service.UserMangementFegin;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

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
            public User findByUserName(String username, String encode) {
                return null;
            }
            @Override
            public Role oleRole(Long id, String encode) {
                return null;
            }
        };
    }
}
