package com.wu.messagepush.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.Query;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wu.messagepush.entity.Message;
import com.wu.messagepush.entity.UserLike;
import com.wu.messagepush.mapper.MessageMapper;
import com.wu.messagepush.mapper.UserLikeMapper;
import com.wu.messagepush.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author sherman
 * @create 2022-11-03 7:45
 * @description
 */
@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    MessageMapper messageMapper;
    @Autowired
    UserLikeMapper userLikeMapper;
    @Override
    public Message updateMessage(Message message) {
        if (message.getId() == null){
            messageMapper.insert(message);
        }
        else{
            messageMapper.updateById(message);
        }
        return message;
    }

    @Override
    public void deleteMessage(Integer id) {
        messageMapper.deleteById(id);
    }

    @Override
    public List<Message> getUnsolvedMessageList() {
        QueryWrapper<Message> queryWrapper = new QueryWrapper<>();
        queryWrapper.ne("state",2);
        return messageMapper.selectList(queryWrapper);
    }

    @Override
    public List<Message> getMessageListByUsername(String username) {
        QueryWrapper<Message> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",username);
        return messageMapper.selectList(queryWrapper);
    }

    @Override
    public Message audit(Integer id, Integer state, String audit) {
        Message message = messageMapper.selectById(id);
        message.setState(state);
        message.setAudit(audit);
        messageMapper.updateById(message);
        return message;
    }

    @Override
    public List<Message> getMessageListForStudent(Integer id) {
        QueryWrapper<Message>queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("mode",1)
                .eq("state",2)
                .or(i -> i.eq("mode",0).eq("object",id).eq("state",2));
        QueryWrapper<UserLike>wrapper = new QueryWrapper<>();
        List<Message>messageList = messageMapper.selectList(queryWrapper);
        messageList.stream().map(message -> {
            Integer like = getUserLike(id,message) ? 1 : 0;
            message.setLikes(like);
            return message;
        }).collect(Collectors.toList());
        return messageList;
    }

    @Override
    public void like(Integer uid, Integer mid) {
        QueryWrapper<UserLike>queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",uid).eq("message_id",mid);
        Long count = userLikeMapper.selectCount(queryWrapper);
        if (count > 0){
            userLikeMapper.delete(queryWrapper);
            decLike(mid);
        }else{
            UserLike userLike = new UserLike();
            userLike.setUserId(uid);
            userLike.setMessageId(mid);
            userLikeMapper.insert(userLike);
            incLike(mid);
        }
    }

    @Override
    public List<Message> getImportantMessageList(Integer count) {
        QueryWrapper<Message>queryWrapper = new QueryWrapper<>();
        queryWrapper.ne("important",1).orderByDesc("likes");
        queryWrapper.last("limit "+count);
        List<Message> messages = messageMapper.selectList(queryWrapper);
        for (Message message : messages) {
            message.setImportant(1);
            messageMapper.updateById(message);
        }
        return messages;
    }

    private void decLike(Integer mid){
        Message message = messageMapper.selectById(mid);
        message.decLike();
        messageMapper.updateById(message);
    }
    private void incLike(Integer mid){
        Message message = messageMapper.selectById(mid);
        message.incLike();
        messageMapper.updateById(message);
    }

    private Boolean getUserLike(Integer uid, Message message){
        QueryWrapper<UserLike>queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",uid)
                .eq("message_id",message.getId());
        return userLikeMapper.selectCount(queryWrapper) > 0 ? true:false;
    }
}
