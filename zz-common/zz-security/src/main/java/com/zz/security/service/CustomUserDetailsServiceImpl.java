package com.zz.security.service;

import com.zz.region.domain.authority.RoleEntity;
import com.zz.region.domain.authority.UserEntity;
import com.zz.security.domain.AuthUser;
import com.zz.zzsystemapi.service.RoleMangementFegin;
import com.zz.zzsystemapi.service.UserMangementFegin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author WQY
 * @Date 2019/11/26 14:55
 * @Version 1.0
 */
@Service
public class CustomUserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private UserMangementFegin userMangementFegin;
    @Resource
    private RoleMangementFegin roleMangementFegin;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserEntity user = userMangementFegin.findByUserName(username);
 
        if (user == null) {
            throw new UsernameNotFoundException("user: " + username + " is not found.");
        }
 
        return new AuthUser(user.getUsername(), user.getPassword(), roleMangementFegin.findByUserRole(user.getId()));
    }
 
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
 
    public static void main(String[] args) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        System.out.println(bCryptPasswordEncoder.encode("123456"));
    }

    public UserEntity findByUserName(String username) {

        return userMangementFegin.findByUserName(username);

    }

    public List<RoleEntity> findByUserRole(Long id) {

        return roleMangementFegin.findByUserRole(id);

    }
}
 