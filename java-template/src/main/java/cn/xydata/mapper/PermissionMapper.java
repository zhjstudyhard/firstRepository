package cn.xydata.mapper;

import cn.xydata.entity.PermissionEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author: haojie
 * @qq :1422471205
 * @CreateTime: 2021-06-29-16-47
 */
@Mapper
public interface PermissionMapper extends MyMapper<PermissionEntity> {
    /**
     * 查询用户权限
     * @param userId
     * @return
     */
    List<String> selectPermissions(String userId);
}
