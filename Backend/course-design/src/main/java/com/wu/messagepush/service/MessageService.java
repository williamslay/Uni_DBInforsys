package com.wu.messagepush.service;

import com.wu.messagepush.entity.Message;

import java.util.List;

/**
 * @author sherman
 * @create 2022-11-03 7:45
 * @description
 */
public interface MessageService {
    Message updateMessage(Message message);
    void deleteMessage(Integer id);
    List<Message>getUnsolvedMessageList();
    List<Message>getMessageListByUsername(String username);
    Message audit(Integer id, Integer state, String audit);
    List<Message>getMessageListForStudent(Integer id);
    void like(Integer uid, Integer mid);
    List<Message> getImportantMessageList(Integer count);
}
