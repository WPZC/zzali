package com.zz.security.service;

import com.zz.region.domain.authority.Role;
import com.zz.region.domain.authority.User;
import com.zz.region.methods.ead.EAD;
import com.zz.security.domain.AuthUser;
import com.zz.zzsystemapi.service.RoleMangementFegin;
import com.zz.zzsystemapi.service.UserMangementFegin;
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

        User user = userMangementFegin.findByUserName(username,EAD.encode());
 
        if (user == null) {
            throw new UsernameNotFoundException("user: " + username + " is not found.");
        }
 
        return new AuthUser(user.getUsername(), user.getPassword(), roleMangementFegin.findByUserRole(user.getId(), EAD.encode()));
    }
 
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
 
    public static void main(String[] args) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        System.out.println(bCryptPasswordEncoder.encode("123456"));
    }

    public User findByUserName(String username,String encode) {

        return userMangementFegin.findByUserName(username,encode);

    }

    public List<Role> findByUserRole(Long id) {

        return roleMangementFegin.findByUserRole(id, EAD.encode());

    }
}
 