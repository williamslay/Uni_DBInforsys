package com.wu.messagepush.service;

import com.wu.messagepush.dto.AdminUserDetails;
import com.wu.messagepush.entity.Role;
import com.wu.messagepush.entity.User;

import java.util.List;

/**
 * @author sherman
 * @create 2022-11-03 7:09
 * @description
 */
public interface LoginService {
    String login(String username,String password);
    User register(User user);
    User updateUser(User user);
    void deleteUser(Integer id);
    List<Role>getRoleList();
    List<User>userList();
    Boolean changePassword(Integer id, String oldPassword, String newPassword);
}
