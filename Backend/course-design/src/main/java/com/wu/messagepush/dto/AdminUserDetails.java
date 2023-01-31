package com.wu.messagepush.dto;

import com.wu.messagepush.entity.Permission;
import com.wu.messagepush.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author sherman
 * @create 2022-11-01 2:42
 * @description SpringSecurity需要的用户详情
 */
public class AdminUserDetails implements UserDetails {
    private User user;
    private List<Permission> permissionList;
    private List<Integer>roleList;

    public AdminUserDetails(User user, List<Permission> permissionList) {
        this.user = user;
        this.permissionList = permissionList;
    }

    public AdminUserDetails(User user, Collection<Integer> roleList) {
        this.user = user;
        this.roleList = (List<Integer>) roleList;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //返回当前用户的权限
        if (permissionList == null)
            return null;
        return permissionList.stream()
                .filter(permission -> permission.getValue()!=null)
                .map(permission ->new SimpleGrantedAuthority(permission.getValue()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
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
