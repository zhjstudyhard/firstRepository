package com.vueblog.search.mq;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author 86181
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostMqIndexMessage implements Serializable {

    // 三种type路由
    public final static String CREATE = "create";
    public final static String UPDATE = "update";
    public final static String REMOVE = "remove";

    private Integer postId;
    private String type;

}
