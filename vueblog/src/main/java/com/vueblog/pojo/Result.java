package com.vueblog.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

/**
 * @Author: haojie
 * @qq :1422471205
 * @CreateTime: 2021-02-02-11-59
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {
    /**
     *响应状态
     */
    private Integer status;
    /**
     * 响应信息
     */
    private String message;
    /**
     * 返回数据
     */
    private Object data;

    /**
     * 设置操作成功重载函数
     * @param data
     * @return
     */
    public static Result success(Object data) {
        Result result = new Result();
        result.setStatus(200);
        result.setMessage("恭喜你,操作成功");
        result.setData(data);
        return result;
    }
    /**
     * 设置操作失败重载函数
     * @param data
     * @return
     */
    public static Result fail(Object data) {
        Result result = new Result();
        result.setStatus(400);
        result.setMessage("操作失败");
        result.setData(data);
        return result;
    }
}
