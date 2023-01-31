package com.wu.messagepush.controller;

import com.wu.messagepush.common.api.CommonResult;
import com.wu.messagepush.entity.Message;
import com.wu.messagepush.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * @author sherman
 * @create 2022-11-03 8:53
 * @description
 */
@RestController
public class MessageController {
    @Autowired
    MessageService messageService;

    @PreAuthorize("hasAuthority('mms:add')")
    @PostMapping("/admin/message")
    public CommonResult updateMessage(@RequestBody Message message){
        return CommonResult.success(messageService.updateMessage(message),"更新或插入推送消息成功");
    }

    @PreAuthorize("hasAuthority('mms:all')")
    @GetMapping("/sysadmin/message")
    public CommonResult getUnsolvedMessages(){
        return CommonResult.success(messageService.getUnsolvedMessageList(),"获取未审核或审核未通过消息列表成功");
    }

    @PreAuthorize("hasAuthority('mms:delete')")
    @DeleteMapping("/admin/message")
    public CommonResult deleteMessage(@RequestParam Integer id){
        messageService.deleteMessage(id);
        return CommonResult.success(null,"删除成功");
    }


    @GetMapping("/admin/message")
    public CommonResult getMessage(@RequestParam String username){
        return CommonResult.success(messageService.getMessageListByUsername(username),"获取当前用户所发推送消息成功");
    }

    @PreAuthorize("hasAuthority('mms:audit')")
    @PostMapping("/admin/audit")
    public CommonResult audit(@RequestParam Integer id, Integer state, String audit){
        return CommonResult.success(messageService.audit(id,state,audit),"审核成功");
    }

    @GetMapping("/message")
    public CommonResult getMessageForStudent(@RequestParam Integer id){
        return CommonResult.success(messageService.getMessageListForStudent(id),"学生或家长获取消息成功");
    }

    @PostMapping("/message")
    public CommonResult like(@RequestParam Integer uid, Integer mid){
        messageService.like(uid,mid);
        return CommonResult.success(null,"点赞成功");
    }

}
