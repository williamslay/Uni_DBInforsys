package com.wu.messagepush.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @author sherman
 * @create 2022-11-02 23:40
 * @description
 */
@Data
public class RolePermissionRelation {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    private Integer roleId;
    private Integer permissionId;
}
