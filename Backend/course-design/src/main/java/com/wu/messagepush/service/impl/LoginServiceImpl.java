package com.wu.messagepush.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wu.messagepush.common.utils.JwtTokenUtil;
import com.wu.messagepush.entity.Role;
import com.wu.messagepush.entity.User;
import com.wu.messagepush.mapper.RoleMapper;
import com.wu.messagepush.mapper.UserMapper;
import com.wu.messagepush.service.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author sherman
 * @create 2022-11-03 7:11
 * @description
 */
@Service
public class LoginServiceImpl implements LoginService {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginServiceImpl.class);
    @Autowired
    UserDetailsService userDetailsService;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    JwtTokenUtil jwtTokenUtil;
    @Autowired
    UserMapper userMapper;
    @Autowired
    RoleMapper roleMapper;
    @Value("$(default.password)")
    private String DEFAULT_PASSWORD;

    @Override
    public String login(String username, String password) {
        String token = null;
        try {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            if (!passwordEncoder.matches(password, userDetails.getPassword())) {
                throw new BadCredentialsException("密码不正确");
            }
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            token = jwtTokenUtil.generateToken(userDetails);
        } catch (AuthenticationException e) {
            LOGGER.warn("登录异常:{}", e.getMessage());
        }
        return token;
    }

    @Override
    public User register(User user) {
        QueryWrapper<User>queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",user.getUsername());
        Long count = userMapper.selectCount(queryWrapper);
        if (count > 0)
            return null;
        //将密码进行加密操作
        String encodePassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodePassword);
        userMapper.insert(user);
        return user;
    }

    @Override
    public User updateUser(User user){
        if (user.getId() == null){
            QueryWrapper<User>queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("username",user.getUsername());
            Long count = userMapper.selectCount(queryWrapper);
            if (count > 0)
                return null;
            if (user.getPassword() == null)
                user.setPassword(DEFAULT_PASSWORD);
            //将密码进行加密操作
            String encodePassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(encodePassword);
            userMapper.insert(user);
            return user;
        }
        if (user.getPassword() != null){
            //将密码进行加密操作
            String encodePassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(encodePassword);
        }
        userMapper.updateById(user);
        return user;
    }

    @Override
    public void deleteUser(Integer id) {
        userMapper.deleteById(id);
    }

    @Override
    public List<Role> getRoleList() {
        return roleMapper.selectList(null);
    }

    @Override
    public List<User> userList() {
        return userMapper.selectList(null);
    }

    @Override
    public Boolean changePassword(Integer id, String oldPassword, String newPassword) {
        if(!validatePassword(id,oldPassword))
            return false;
        User user = new User();
        user.setId(id);
        user.setPassword(passwordEncoder.encode(newPassword));
        userMapper.updateById(user);
        return true;
    }

    public Boolean validatePassword(Integer id, String password){
        String true_value = userMapper.selectById(id).getPassword();
        return passwordEncoder.matches(password,true_value);
    }
}
