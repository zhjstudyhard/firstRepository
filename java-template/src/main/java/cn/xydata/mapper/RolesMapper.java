package cn.xydata.mapper;

import cn.xydata.entity.RoleEntity;
import cn.xydata.entity.UserRoleEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author: haojie
 * @qq :1422471205
 * @CreateTime: 2021-06-29-16-38
 */
@Mapper
public interface RolesMapper extends MyMapper<RoleEntity> {
     /**
      * 查询用户的角色
      * @param userId
      * @return
      */
     List<String> selectRoles(String userId);


}
