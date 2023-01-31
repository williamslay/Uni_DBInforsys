package com.wu.messagepush.service.impl;

import com.wu.messagepush.entity.Permission;
import com.wu.messagepush.entity.RolePermissionRelation;
import com.wu.messagepush.entity.User;
import com.wu.messagepush.mapper.PermissionMapper;
import com.wu.messagepush.mapper.RolePermissionMapper;
import com.wu.messagepush.mapper.UserMapper;
import com.wu.messagepush.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author sherman
 * @create 2022-11-03 0:08
 * @description
 */
@Service
public class UserServiceImpl implements UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    UserMapper userMapper;
    @Autowired
    PermissionMapper permissionMapper;
    @Autowired
    RolePermissionMapper rolePermissionMapper;

    @Override
    public User getUserByUsername(String username) {
        Map<String,Object>map = new HashMap<>();
        map.put("username",username);
        List<User>users = userMapper.selectByMap(map);
        if (users != null && users.size() > 0)
            return users.get(0);
        return null;
    }

    @Override
    public List<Permission> getPermissionList(Integer id) {
        User user = userMapper.selectById(id);
        Map<String,Object>map = new HashMap<>();
        map.put("role_id",user.getRoleId());
        LOGGER.info("roleId"+user.getRoleId());
        LOGGER.info("role_username"+user.getPassword());
        List<RolePermissionRelation> rolePermissionList = rolePermissionMapper.selectByMap(map);
        if (rolePermissionList != null && rolePermissionList.size() > 0){
            List<Integer>permissionIDList = rolePermissionList.stream().map(RolePermissionRelation::getPermissionId).collect(Collectors.toList());
            List<Permission> permissionList = permissionMapper.selectBatchIds(permissionIDList);
            if (permissionList != null && permissionList.size() > 0)
                return permissionList;
            return null;
        }
        return null;
    }
}
