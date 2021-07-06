package cn.xydata.service;

import cn.xydata.common.dto.QueryParamsDto;
import cn.xydata.common.dto.ResponsePageDto;
import cn.xydata.dto.PermDto;
import cn.xydata.dto.PermRequestPathDto;
import cn.xydata.entity.PermissionEntity;
import cn.xydata.vo.PermPathVo;
import java.util.List;

/**
 * @Author: haojie
 * @qq :1422471205
 * @CreateTime: 2021-07-01-14-51
 */
public interface PermissionService {


    /**
     * 创建权限
     * @param permDto
     * @throws Exception
     */
    void createPermission(PermDto permDto) throws Exception;

    /**
     * 更改权限
     * @param permDto
     * @throws Exception
     */
    void updatePermission(PermDto permDto) throws Exception;

    /**
     * 删除权限
     *
     * @param id
     */
    void delPermission(String id);

    /**
     * 分页查询
     *
     * @param queryParamsDto
     * @return
     * @throws Exception
     */
    ResponsePageDto queryPermsPage(QueryParamsDto queryParamsDto) throws Exception;

    /**
     * 查询权限请求列表
     * @param permId
     * @return
     * @throws Exception
     */
    List<PermPathVo> queryPermUris(String permId) throws Exception;

    /**
     * 权限url设置
     * @param permRequestPathDto
     */
    void assignPermsUrl(PermRequestPathDto permRequestPathDto);

    /**
     * 查询所有权限
     * @return
     */
    List<PermissionEntity> queryUserList();
}
