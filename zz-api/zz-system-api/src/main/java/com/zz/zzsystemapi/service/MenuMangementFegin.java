package com.zz.zzsystemapi.service;

import com.zz.ServiceNameConstants;
import com.zz.zzsystemapi.factory.RemoteMenuFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * 菜单fegin  （调用指定的数据源）
 * @author wqy
 * @version 1.0
 * @date 2020/6/9 16:31
 */
@FeignClient(contextId = "menuMangementFegin", value = ServiceNameConstants.SYSTEM_SERVICE,fallbackFactory = RemoteMenuFallbackFactory.class)
public interface MenuMangementFegin {


}
