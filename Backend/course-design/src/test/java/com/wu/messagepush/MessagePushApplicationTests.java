package com.wu.messagepush;

import com.wu.messagepush.entity.User;
import com.wu.messagepush.mapper.RoleMapper;
import com.wu.messagepush.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@SpringBootTest
class MessagePushApplicationTests {
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private UserMapper userMapper;


    @Test
    void contextLoads() {
        Map<String,Object> map = new HashMap<>();
        map.put("username","sherman");
        List<User> users = userMapper.selectByMap(map);
        users.forEach(System.out::println);
    }

}
