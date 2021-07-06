package cn.xydata.mapper;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @Author: haojie
 * @qq :1422471205
 * @CreateTime: 2021-06-29-10-06
 */

public interface MyMapper<T> extends Mapper<T>, MySqlMapper<T> {
}
