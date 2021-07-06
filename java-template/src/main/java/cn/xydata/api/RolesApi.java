package cn.xydata.api;

import cn.xydata.common.annotation.Error;
import cn.xydata.common.annotation.SysControllerLogger;
import cn.xydata.common.domain.result.ResponseData;
import cn.xydata.common.dto.QueryParamsDto;
import cn.xydata.common.exception.CustomException;
import cn.xydata.dto.DelRoleDto;
import cn.xydata.dto.RoleDto;
import cn.xydata.dto.RolePermissionDto;
import cn.xydata.entity.BaseEntity;

import cn.xydata.service.RolesService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


/**
 * @Author: haojie
 * @qq :1422471205
 * @CreateTime: 2021-06-30-15-05
 */
@RestController
@RequestMapping("/api/userRoles")
public class RolesApi extends BaseEntity {

    @Autowired
    private RolesService rolesService;
    /**
     * 查找所有的数据且不需要分页
     * @return
     * @throws Exception
     */
    @Error
    @RequestMapping(value = "/listNotPage", method = RequestMethod.POST)
    public Object queryUserList() throws Exception {
        return rolesService.queryUserList();
    }

    /**
     * 查询角色信息
     *
     * @return
     */
    @SysControllerLogger(description = "查询角色信息")
    @Error
    @RequestMapping(value = "/queryUserRoles", method = RequestMethod.POST)
    public Object queryUserRoles(@RequestBody QueryParamsDto queryParamsDto) throws Exception {

        return rolesService.queryUserRoles(queryParamsDto);

    }

    /**
     * 创建角色
     *
     * @return
     */
    @SysControllerLogger(description = "创建角色")
    @Error
    @RequestMapping(value = "/createRole", method = {RequestMethod.POST})
    public Object createRole(@Valid @RequestBody RoleDto roleDto) throws Exception {
        rolesService.createRole(roleDto);
        return null;
    }

    /**
     * 更新角色
     *
     * @return
     */
    @SysControllerLogger(description = "更新角色信息")
    @Error
    @RequestMapping(value = "/updateRole", method = {RequestMethod.POST})
    public Object updateRole(@Valid @RequestBody RoleDto roleDto) {
        if (roleDto.getId() == null) {
            throw new CustomException("用户id不能为空");
        }
        rolesService.updateRole(roleDto);
        return null;
    }

    /**
     * 删除角色信息
     *
     * @return
     */
    @SysControllerLogger(description = "删除角色信息")
    @Error
    @RequestMapping(value = "/deleteRole", method = {RequestMethod.POST})
    public Object deleteRole(@RequestBody DelRoleDto delRoleDto) {
        //判断id是否为空
        if (StringUtils.isEmpty(delRoleDto.getId())) {
            throw new CustomException("角色id不能为空");
        }
        rolesService.deleteRole(delRoleDto.getId());
        return null;
    }

    /**
     * 查询角色权限
     *
     * @return
     */
    @SysControllerLogger(description = "查询角色权限")
    @Error
    @RequestMapping(value = "/queryPermissionByRoleId", method = {RequestMethod.POST})
    public Object queryPermissionByRoleId(@RequestBody RolePermissionDto rolePermissionDto) {
        if (StringUtils.isEmpty(rolePermissionDto.getRoleId())) {
            throw new CustomException("角色名不能为空");
        }
        return rolesService.queryPermissionByRoleId(rolePermissionDto.getRoleId());
    }

    /**
     * 修改角色权限
     *
     * @return
     */
    @SysControllerLogger(description = "修改角色权限")
    @Error
    @RequestMapping(value = "/updatePermission", method = RequestMethod.POST)
    public Object updatePermission(@RequestBody RolePermissionDto rolePermissionDto) {
        //校验数据
        if (rolePermissionDto.getRoleId() == null) {
            throw new CustomException("角色id不能为空");
        }
        rolesService.updatePermission(rolePermissionDto);
        return null;
    }

}
