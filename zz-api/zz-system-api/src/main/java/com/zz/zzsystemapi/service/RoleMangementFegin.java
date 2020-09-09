package com.zz.zzsystemapi.service;

import com.zz.region.ServiceNameConstants;
import com.zz.region.domain.PageData;
import com.zz.region.domain.authority.Menu;
import com.zz.region.domain.authority.Role;
import com.zz.region.domain.authority.RoleMenu;
import com.zz.region.vo.ResultVO;
import com.zz.zzsystemapi.factory.RemoteRoleFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author wqy
 * @version 1.0
 * @date 2020/5/28 11:34
 */
@FeignClient(contextId = "roleMangementFegin", value = ServiceNameConstants.SYSTEM_SERVICE, fallbackFactory = RemoteRoleFallbackFactory.class)
public interface RoleMangementFegin {

    /**
     * 查询用户角色
     * @param id
     * @return
     */
    @PostMapping(value = "/ac/findByUserRole")
    List<Role> findByUserRole(@RequestParam("id") Long id,@RequestParam("encode") String encode);

}
