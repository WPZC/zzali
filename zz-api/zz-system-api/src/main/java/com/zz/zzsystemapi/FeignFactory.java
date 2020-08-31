package com.zz.zzsystemapi;

import com.zz.zzsystemapi.service.RoleMangementFegin;
import com.zz.zzsystemapi.service.UserMangementFegin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author wqy
 * @version 1.0
 * @date 2020/8/31 15:17
 */
@Component
public class FeignFactory {

    @Resource
    private RoleMangementFegin roleMangementFegin;

    @Resource
    private UserMangementFegin userMangementFegin;

    public RoleMangementFegin roleMangementFegin(){
        return roleMangementFegin;
    };

    public UserMangementFegin userMangementFegin(){
        return userMangementFegin;
    };

}
