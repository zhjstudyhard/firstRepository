package cn.xydata.service;

import cn.xydata.common.dto.QueryParamsDto;
import cn.xydata.common.dto.ResponsePageDto;
import cn.xydata.dto.UserDto;
import cn.xydata.dto.UserRoleDto;
import cn.xydata.entity.UserEntity;
import cn.xydata.vo.UserRoleVo;
import cn.xydata.vo.UserVo;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * @author 86181
 */
public interface UserService {
    
    Object test(UserEntity userEntity);

    /**
     * 用户注册
     * @param userDto
     * @throws Exception
     */
    void createUser(UserDto userDto) throws Exception;


    /**
     * 分页查询用户
     * @param queryParamsDto
     * @return
     * @throws Exception
     */
    ResponsePageDto queryUserPage(QueryParamsDto queryParamsDto) throws Exception;


    /**
     * 用户编辑或更新
     * @param userDto
     * @throws Exception
     */
    void updateUser(UserDto userDto) throws Exception;

    /**
     * 锁定用户
     * @param id
     */
    void updateUserLockById(String id);

    /**
     * 解锁用户
     * @param id
     * @throws Exception
     */
    void unlockUser(String id) throws Exception;

    /**
     * 停用用户
     * @param id
     */
    void disabledUser(String id);

    /**
     * 启用用户
     * @param id
     */
    void enabledUser(String id);

    /**
     * 授予角色
     * @param userRoleDto
     */
    void assign(UserRoleDto userRoleDto);

    /**
     * 删除用户
     * @param userId
     */
    void deleteUser(String userId);

    /**
     * 查询用户角色
     * @param id
     * @return
     * @throws Exception
     */
    List<UserRoleVo> userRoles(String id) throws  Exception;

    /**
     * 查询所有用户
     * @return
     */
    List<UserEntity> queryUserList();
}