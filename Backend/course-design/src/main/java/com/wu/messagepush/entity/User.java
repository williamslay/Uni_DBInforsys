package com.wu.messagepush.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @author sherman
 * @create 2022-11-01 2:49
 * @description 用户实体类
 */
@Data
public class User {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    private String username;
    private String password;
    private Integer roleId;
    private String name;
    private String email;
    private String phone;
}
