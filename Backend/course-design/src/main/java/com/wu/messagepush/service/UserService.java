package com.wu.messagepush.service;

import com.wu.messagepush.entity.Permission;
import com.wu.messagepush.entity.User;

import java.util.List;

/**
 * @author sherman
 * @create 2022-11-03 0:08
 * @description
 */
public interface UserService {
    User getUserByUsername(String username);
    List<Permission> getPermissionList(Integer id);
}
