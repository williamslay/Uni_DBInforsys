package com.wu.messagepush.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @author sherman
 * @create 2022-11-01 2:46
 * @description
 */
@Data
public class Permission {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    private String name;
    private Integer status;
    private String value;
}
