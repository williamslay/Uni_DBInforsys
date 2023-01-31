package com.wu.messagepush.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @author sherman
 * @create 2022-11-02 22:49
 * @description
 */
@Data
public class Role {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    private String name;
    private String description;
    private Integer status;
}
