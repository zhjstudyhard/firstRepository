package cn.xydata.service.impl;

import cn.xydata.common.dto.QueryParamsDto;
import cn.xydata.common.dto.ResponsePageDto;
import cn.xydata.common.exception.CustomException;
import cn.xydata.dto.RoleDto;
import cn.xydata.dto.RolePermissionDto;
import cn.xydata.entity.RoleEntity;
import cn.xydata.entity.RolePermissionEntity;
import cn.xydata.entity.UserEntity;
import cn.xydata.entity.UserRoleEntity;
import cn.xydata.mapper.RolesMapper;
import cn.xydata.mapper.RolesPermissionMapper;
import cn.xydata.mapper.UserRoleMapper;
import cn.xydata.service.RolesService;
import cn.xydata.utils.BeanCopyUtils;
import cn.xydata.vo.RoleVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.*;

/**
 * @Author: haojie
 * @qq :1422471205
 * @CreateTime: 2021-06-29-16-31
 */
@Service
public class RolesServiceImpl implements RolesService {

    @Autowired
    private RolesMapper rolesMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private RolesPermissionMapper rolesPermissionMapper;

    /**
     * 分页查询所有角色
     *
     * @return
     */
    @Override
    public ResponsePageDto queryUserRoles(QueryParamsDto queryParamsDto) throws Exception{
        //开启分页
        PageHelper.startPage(Integer.parseInt(queryParamsDto.getCurrent()), Integer.parseInt(queryParamsDto.getPageSize()));
        //构建查询条件
        HashMap<String, Object> searchObject = queryParamsDto.getSearchObject();
        String roleCode = (String) searchObject.get("roleCode");
        String roleName = (String) searchObject.get("roleName");

        Example example = new Example(RoleEntity.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("isDeleted",0);
        if (!StringUtils.isEmpty(roleCode)) {
            criteria.andLike("roleCode", "%" + roleCode + "%");
        }
        if (!StringUtils.isEmpty(roleName) ){
            criteria.andLike("roleName", "%" + roleName + "%");
        }

        //开始查询
        List<RoleEntity> roleEntities = rolesMapper.selectByExample(example);
        System.out.println("查询数量："+roleEntities.size());
        PageInfo<RoleEntity> pageInfo = new PageInfo<RoleEntity>(roleEntities);

        //转换VO
        List<RoleVo> roleVos = BeanCopyUtils.getCopyList(pageInfo.getList(), RoleVo.class);

        return new ResponsePageDto(roleVos, pageInfo.getTotal(),pageInfo.getPageSize(),pageInfo.getPageNum());
    }

    /**
     * 判断角色是否唯一
     * @param roleDto
     */
    public void checkRole(RoleDto roleDto){
        //判断角色代码是否重复
        Example example = new Example(RoleEntity.class);
        example.createCriteria().andEqualTo("roleCode",roleDto.getRoleCode())
                .andEqualTo("isDeleted",0);
        RoleEntity roleEntity = rolesMapper.selectOneByExample(example);
        if (roleEntity != null && !roleEntity.getId().equals(roleDto.getId())) {
            throw new CustomException("角色代码重复");
        }
    }

    /**
     * 创建角色
     *
     * @param roleDto
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void createRole(RoleDto roleDto) throws Exception{
        //判断角色编码重复
        Example example = new Example(RoleEntity.class);
        example.createCriteria().andEqualTo("roleCode",roleDto.getRoleCode());
        if (rolesMapper.selectByExample(example).size() > 0){
            throw new CustomException("角色编码重复");
        }

        //判断角色名称重复
        Example example2 = new Example(RoleEntity.class);
        example2.createCriteria().andEqualTo("roleName",roleDto.getRoleName());
        if (rolesMapper.selectByExample(example2).size() > 0){
            throw new CustomException("角色名称重复");
        }

        //dto转换成实体类
        RoleEntity roleEntity = roleDto.getEntity(RoleEntity.class);
        roleEntity.setGmtCreate(new Date());
        roleEntity.setIsDeleted(0);
        rolesMapper.insert(roleEntity);
    }

    /**
     * 更新角色信息
     *
     * @param roleDto
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateRole(RoleDto roleDto) {
        //判断角色是否被删除
        RoleEntity roleEntity = rolesMapper.selectByPrimaryKey(roleDto.getId());
        if (roleEntity == null){
            throw new CustomException("查无此人");
        }

        //判断添加属性是否唯一
        checkRole(roleDto);

        //设置属性
        roleEntity.setRoleCode(roleDto.getRoleCode());
        roleEntity.setRoleName(roleDto.getRoleName());
        roleEntity.setGmtModified(new Date());

        //更新角色信息
        rolesMapper.updateByPrimaryKey(roleEntity);
    }

    /**
     * 根据角色id删除角色
     *
     * @param id
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteRole(String id) {

        //判断该角色是否存在
        RoleEntity roleEntity = rolesMapper.selectByPrimaryKey(id);
        if (roleEntity == null) {
            throw new CustomException("查无此人");
        }

        //删除关联表数据
        Example example = new Example(UserRoleEntity.class);
        example.createCriteria().andEqualTo("roleId",roleEntity.getId());
        userRoleMapper.deleteByExample(example);

//        int a = 1 / 0;
        //更新角色
        roleEntity.setGmtModified(new Date());
        roleEntity.setIsDeleted(1);
        rolesMapper.updateByPrimaryKey(roleEntity);
    }

    /**
     * 根据角色id查询权限
     *
     * @param roleId
     * @return
     */
    @Override
    public List<RolePermissionEntity> queryPermissionByRoleId(String roleId) {
        //查询条件
        Example example = new Example(RolePermissionEntity.class);
        example.createCriteria().andEqualTo("roleId",roleId)
                .andEqualTo("isDeleted",0);

        //查询对象权限
        List<RolePermissionEntity> rolePermissionEntities = rolesPermissionMapper.selectByExample(example);
        return rolePermissionEntities;
    }

    /**
     * 修改角色权限
     * @param rolePermissionDto
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updatePermission(RolePermissionDto rolePermissionDto) {

        RoleEntity roleEntity = rolesMapper.selectByPrimaryKey(rolePermissionDto.getRoleId());
        if (roleEntity == null) {
            throw new CustomException("查无此角色");
        }

        //删除关联表数据
        Example example = new Example(RolePermissionEntity.class);
        example.createCriteria().andEqualTo("roleId",rolePermissionDto.getRoleId());
        rolesPermissionMapper.deleteByExample(example);

        //添加关联表数据
        for (String permId : rolePermissionDto.getPermIds()) {
            RolePermissionEntity rolePermissionEntity = new RolePermissionEntity();
            rolePermissionEntity.setGmtCreate(new Date());
            roleEntity.setIsDeleted(0);
            rolePermissionEntity.setRoleId(rolePermissionDto.getRoleId());
            rolePermissionEntity.setPermId(permId);
            rolesPermissionMapper.insert(rolePermissionEntity);
        }
    }

    /**
     * 查询所有角色
     *
     * @return
     */
    @Override
    public List<RoleEntity> queryUserList() {
        //查询条件
        Example example = new Example(UserEntity.class);
        example.createCriteria().andEqualTo("isDeleted",0);

        //执行查询
        List<RoleEntity> roleEntities = rolesMapper.selectByExample(example);

        return roleEntities;
    }


}
