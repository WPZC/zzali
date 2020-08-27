package com.zz.security.feign;

import com.zz.region.domain.authority.RoleEntity;
import com.zz.region.domain.authority.UserEntity;
import com.zz.region.vo.ResultVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author wqy
 * @version 1.0
 * @date 2020/5/28 11:06
 */
@FeignClient(value = "zz-system-api")
public interface UserMangementFegin {

    @PostMapping(value = "/ac/findByUserName")
    UserEntity findByUserName(@RequestParam("username") String username);

    @PostMapping(value = "/uct/addUser")
    ResultVO<String> addUser(@RequestBody UserEntity userEntity, @RequestParam("roleId")Long roleId);

    @RequestMapping(value = "/uct/updateUser")
    ResultVO<String> updateUser(@RequestBody UserEntity userEntity,@RequestParam("roleId")Long roleId);

    /**
     * 删除用户
     * @param id
     * @return
     */
    @PostMapping(value = "/uct/deleteByuId")
    ResultVO<String> deleteByuId(@RequestParam("id") Long id);

    /**
     * 根据用户id查找角色对象
     * @param id
     * @return
     */
    @PostMapping(value = "/uct/oleRole")
    RoleEntity oleRole(@RequestParam("id") Long id);
}
