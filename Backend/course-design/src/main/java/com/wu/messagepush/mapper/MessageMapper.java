package com.wu.messagepush.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wu.messagepush.entity.Message;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author sherman
 * @create 2022-11-02 23:04
 * @description
 */
@Mapper
public interface MessageMapper extends BaseMapper<Message> {
}
