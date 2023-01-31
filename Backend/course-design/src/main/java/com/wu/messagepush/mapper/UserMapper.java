package com.wu.messagepush.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wu.messagepush.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author sherman
 * @create 2022-11-03 10:39
 * @description
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
