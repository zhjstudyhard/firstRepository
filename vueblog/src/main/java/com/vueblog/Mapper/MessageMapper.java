package com.vueblog.Mapper;

import com.vueblog.pojo.UserMessage;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: haojie
 * @qq :1422471205
 * @CreateTime: 2021-03-21-15-28
 */
@Mapper
public interface MessageMapper {
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
