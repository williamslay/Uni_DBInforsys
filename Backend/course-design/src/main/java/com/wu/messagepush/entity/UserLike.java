package com.wu.messagepush.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @author sherman
 * @create 2022-11-03 8:13
 * @description
 */
@Data
public class UserLike {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    private Integer userId;
    private Integer messageId;
}
