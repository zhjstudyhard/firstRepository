package com.vueblog.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author: haojie
 * @qq :1422471205
 * @CreateTime: 2021-03-14-13-30
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Page<T> {
    public static final Integer page_SIZE = 2;
    /**
     * 当前页码
     */
    private  Integer pageNum;

    /**
     * 页面数量
     */
    private Integer pageTotal;

    /**
     * 总记录数
     */
    private Integer pageTotalCount;

    /**
     * 每页显示数量
     */
    private Integer pageSize = page_SIZE;

    /**
     * 保存当前页面数据
     */
    private List<T> items;

    /**
     * 计算得到总页数
     * @return
     */
    public Integer setPageNums(){
        if(pageTotalCount % pageSize == 0){
            pageTotal = pageTotalCount / pageSize;
        }else {
            pageTotal = pageTotalCount / pageSize + 1;
        }
        return pageTotal;
    }
}
