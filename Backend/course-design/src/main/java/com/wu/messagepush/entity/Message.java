package com.wu.messagepush.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author sherman
 * @create 2022-11-02 22:58
 * @description
 */
@Data
public class Message {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    private String username;
    private String author;
    private String title;
    private String content;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GMT+8")
    private Date time;
    private String type;
    private Integer mode;
    private Integer object;
    private Integer state;
    private String audit;
    private Integer likes;
    private Integer important;

    public void incLike(){
        this.likes++;
    }
    public void decLike(){
        this.likes--;
    }
}
