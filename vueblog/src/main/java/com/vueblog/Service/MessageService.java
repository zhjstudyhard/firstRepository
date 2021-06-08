package com.vueblog.Service;

import com.vueblog.pojo.UserMessage;

/**
 * @Author: haojie
 * @qq :1422471205
 * @CreateTime: 2021-03-21-15-22
 */
public interface MessageService {
    /**
     * 保存通知信息
     * @param userMessage
     */
    void save(UserMessage userMessage);

    /**
     * 查看未读消息数量
     * @return
     */
    Integer noReadMessage(Integer userId);
}
