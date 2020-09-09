package com.zz.security.domain;

import com.zz.region.domain.authority.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author WQY
 * @Date 2019/11/26 14:52
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthUser implements UserDetails {
 
    private String username;
    private String password;
    private List<Role> roles;
 
 
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.isEmpty() ? Collections.EMPTY_LIST :
// ROLE_ 是springsecurity对于角色的默认前缀，如果不加，验证会失败
                (roles.parallelStream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList()));    }
 
    @Override
    public String getPassword() {
        return password;
    }
 
    @Override
    public String getUsername() {
        return username;
    }
 
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
 
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
 
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
 
    @Override
    public boolean isEnabled() {
        return true;
    }
}