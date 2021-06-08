package com.vueblog.Service.Impl;

import com.vueblog.Mapper.MessageMapper;
import com.vueblog.Service.MessageService;
import com.vueblog.pojo.UserMessage;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: haojie
 * @qq :1422471205
 * @CreateTime: 2021-03-21-15-23
 */
@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    private MessageMapper messageMapper;
    /**
     * 保存通知信息
     * @param userMessage
     */
    @Override
    public void save(UserMessage userMessage) {
        messageMapper.save(userMessage);
    }

    /**
     * 查看未读消息数量
     *
     * @return
     */
    @Override
    public Integer noReadMessage(Integer userId) {
        return messageMapper.noReadMessage(userId);
    }
}
