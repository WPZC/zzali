package com.zz.zzsystemapi.factory;

import com.zz.zzsystemapi.service.MenuMangementFegin;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 用户服务降级处理
 * 
 * @author ruoyi
 */
@Component
public class RemoteMenuFallbackFactory implements FallbackFactory<MenuMangementFegin>
{
    @Override
    public MenuMangementFegin create(Throwable throwable)
    {
        return new MenuMangementFegin()
        {

        };
    }
}
