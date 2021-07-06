package cn.xydata.service;

import cn.xydata.common.dto.QueryParamsDto;
import cn.xydata.common.dto.ResponsePageDto;
import cn.xydata.dto.RoleDto;
import cn.xydata.dto.RolePermissionDto;
import cn.xydata.entity.RoleEntity;
import cn.xydata.entity.RolePermissionEntity;

import java.util.List;

/**
 * @Author: haojie
 * @qq :1422471205
 * @CreateTime: 2021-06-29-16-31
 */
public interface RolesService {

    /**
     * 分页查询所有角色
     * @param queryParamsDto
     * @return
     * @throws Exception
     */
    ResponsePageDto queryUserRoles(QueryParamsDto queryParamsDto) throws Exception;

    /**
     * 创建角色
     * @param roleDto
     * @throws Exception
     */
    void createRole(RoleDto roleDto) throws Exception;

    /**
     * 更新角色信息
     * @param roleDto
     */
    void updateRole(RoleDto roleDto);

    /**
     * 根据角色id删除角色
     * @param id
     */
    void deleteRole(String id);

    /**
     * 根据角色id查询权限
     * @param roleId
     * @return
     */
    List<RolePermissionEntity> queryPermissionByRoleId(String roleId);

    /**
     * 修改角色权限
     * @param rolePermissionDto
     */
    void updatePermission(RolePermissionDto rolePermissionDto);

    /**
     * 查询所有角色
     * @return
     */
    List<RoleEntity> queryUserList();
}
