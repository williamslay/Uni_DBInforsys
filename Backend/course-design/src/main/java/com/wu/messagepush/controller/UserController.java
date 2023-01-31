package com.wu.messagepush.controller;

import com.wu.messagepush.common.api.CommonResult;
import com.wu.messagepush.common.utils.RedisUtil;
import com.wu.messagepush.dto.UserLoginParam;
import com.wu.messagepush.entity.User;
import com.wu.messagepush.service.LoginService;
import com.wu.messagepush.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author sherman
 * @create 2022-11-02 23:45
 * @description
 */
@RestController
public class UserController {
    @Autowired
    LoginService loginService;
    @Autowired
    UserService userService;
    @Autowired
    RedisUtil redisUtil;
    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;
    @Value("${redis.key.token.prefix}")
    private String redisPrefix;
    @Value("${redis.key.token.expire}")
    private Long redisExpiration;

    @PostMapping("/login")
    public CommonResult login(@RequestBody UserLoginParam userLoginParam){
        String token = loginService.login(userLoginParam.getUsername(), userLoginParam.getPassword());
        if (token == null) {
            return CommonResult.validateFailed("用户名或密码错误");
        }
        redisUtil.set(redisPrefix+userLoginParam.getUsername(),new Date());
        redisUtil.expire(redisPrefix+userLoginParam.getUsername(),redisExpiration);
        Map<String, Object> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);
        User user = userService.getUserByUsername(userLoginParam.getUsername());
        tokenMap.put("userinfo",user);
        return CommonResult.success(tokenMap,"登录成功");
    }

    @PostMapping("/register")
    public CommonResult<User> register(@RequestBody User userParam) {
        User user = loginService.register(userParam);
        if (user == null) {
            return CommonResult.failed("注册失败");
        }
        return CommonResult.success(user,"注册成功");
    }

    @PreAuthorize("hasAuthority('ums:add')")
    @PostMapping("/admin/userInfo")
    public CommonResult<User> updateUser(@RequestBody User userParam){
        int flag=0;
        if (userParam.getId()!=null)
            flag=1;
        User user = loginService.updateUser(userParam);
        if (user==null)
            return CommonResult.failed("用户名重复");
        if(flag==0)
            return CommonResult.success(user,"用戶添加成功");
        return CommonResult.success(user,"用戶修改成功");
    }

    @PreAuthorize("hasAuthority('ums:delete')")
    @DeleteMapping("/admin/userInfo")
    public CommonResult<Integer> deleteUser(@RequestParam Integer id){
        loginService.deleteUser(id);
        return CommonResult.success(null,"用戶删除成功");
    }

    @PreAuthorize("hasAuthority('ums:userinfo')")
    @GetMapping("/admin/userInfo")
    public CommonResult userList(){
        Map<String,Object>map=new HashMap<>();
        map.put("userList",loginService.userList());
        map.put("roles",loginService.getRoleList());
        return CommonResult.success(map,"获取用戶列表成功");
    }

    @PostMapping("/changePassword")
    public CommonResult validatePassword(@RequestParam Integer id, String oldPassword, String newPassword){
        Boolean value = loginService.changePassword(id,oldPassword,newPassword);
        if (value)
            return CommonResult.success(null,"修改密码成功");
        else
            return CommonResult.failed("修改密码失败");
    }
}
