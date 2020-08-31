package com.zz.zzsystemapi.service;

import com.zz.region.ServiceNameConstants;
import com.zz.region.domain.authority.RoleEntity;
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
    List<RoleEntity> findByUserRole(@RequestParam("id") Long id);

    /**
     * 添加角色
     * @param name
     * @param description
     * @return
     */
    @PostMapping(value = "/rct/addRole")
    ResultVO<RoleEntity> addRole(@RequestParam("name") String name, @RequestParam("description") String description);

    /**
     * 删除角色
     * @param rId
     * @return
     */
    @PostMapping(value = "/rct/deleteByRId")
    ResultVO<String> deleteByRId(@RequestParam("rId") Long rId);

    /**
     * 修改橘色
     * @param roleEntity
     * @return
     */
    @PostMapping(value = "/rct/updateRole")
    ResultVO<RoleEntity> updateRole(@RequestBody RoleEntity roleEntity);

    /**
     * 获取角色列表List
     * @return
     */
    @PostMapping(value = "/rct/getRoleMsgsList")
    ResultVO<List<RoleEntity>> getRoleMsgsList();
}
