package cn.xydata.mapper;

import cn.xydata.entity.RequestPathEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author: haojie
 * @qq :1422471205
 * @CreateTime: 2021-07-04-13-41
 */
@Mapper
public interface RequestPathMapper extends MyMapper<RequestPathEntity>{
    /**
     * 查询对应的url
     * @param userId
     * @return
     */
    List<String> selectRequestPaths(String userId);
}
