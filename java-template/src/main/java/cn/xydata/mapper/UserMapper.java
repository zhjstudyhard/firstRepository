package cn.xydata.mapper;

import cn.xydata.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @Author: haojie
 * @qq :1422471205
 * @CreateTime: 2021-06-30-10-10
 */
@Mapper
public interface UserMapper extends MyMapper<UserEntity>{
    /**
     * 查询用户信息
     * @param id
     * @return
     */
    UserEntity queryUserById(String id);

    /**
     * 锁定用户
     * @param id
     */
    @Update("update t_aaa_user set locked = 1 where id = #{id}")
    void updateUserLockById(String id);

    /**
     * 解锁用户
     * @param id
     */
    @Update("update t_aaa_user set locked = 0 where id = #{id}")
    void unlockUser(String id);

    /**
     * 停用用户
     * @param id
     */
    @Update("update t_aaa_user set enabled = 1 where id = #{id}")
    void disabledUser(String id);

    /**
     * 启用用户
     * @param id
     */
    @Update("update t_aaa_user set enabled = 0 where id = #{id}")
    void enabledUser(String id);


}
