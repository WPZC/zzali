package com.zz.zzsystemapi.service;

import com.zz.ServiceNameConstants;
import com.zz.domain.authority.Role;
import com.zz.domain.authority.User;
import com.zz.zzsystemapi.factory.RemoteUserFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author wqy
 * @version 1.0
 * @date 2020/5/28 11:06
 */
@FeignClient(contextId = "userMangementFegin", value = ServiceNameConstants.SYSTEM_SERVICE, fallbackFactory = RemoteUserFallbackFactory.class)
public interface UserMangementFegin {

    @PostMapping(value = "/ac/findByUserName")
    User findByUserName(@RequestParam("username") String username, @RequestParam("encode") String encode);

    /**
     * 根据用户id查找角色对象
     * @param id
     * @return
     */
    @PostMapping(value = "/uct/oleRole")
    Role oleRole(@RequestParam("id") Long id, @RequestParam("encode") String encode);
}
