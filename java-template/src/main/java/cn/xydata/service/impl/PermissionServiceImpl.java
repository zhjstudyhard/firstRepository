package cn.xydata.service.impl;

import cn.xydata.common.domain.result.Response;
import cn.xydata.common.dto.QueryParamsDto;
import cn.xydata.common.dto.ResponsePageDto;
import cn.xydata.common.exception.CustomException;
import cn.xydata.constant.Constant;
import cn.xydata.dto.PermDto;
import cn.xydata.dto.PermRequestPathDto;
import cn.xydata.dto.RoleDto;
import cn.xydata.entity.*;
import cn.xydata.mapper.PermRequestPathMapper;
import cn.xydata.mapper.PermissionMapper;
import cn.xydata.mapper.RolesPermissionMapper;
import cn.xydata.service.PermissionService;
import cn.xydata.utils.BeanCopyUtils;
import cn.xydata.vo.PermPathVo;
import cn.xydata.vo.PermissionVo;
import cn.xydata.vo.UserVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

/**
 * @Author: haojie
 * @qq :1422471205
 * @CreateTime: 2021-07-01-14-51
 */
@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    @Autowired
    private RolesPermissionMapper rolesPermissionMapper;

    @Autowired
    private PermRequestPathMapper permRequestPathMapper;
    /**
     * 分页查询权限
     *
     * @param queryParamsDto
     * @return
     */
    @Override
    public ResponsePageDto queryPermsPage(QueryParamsDto queryParamsDto) throws Exception {
        //开启分页
        PageHelper.startPage(Integer.parseInt(queryParamsDto.getCurrent()), Integer.parseInt(queryParamsDto.getPageSize()));
        //构建查询条件
        HashMap<String, Object> searchObject = queryParamsDto.getSearchObject();
        String permCode = (String) searchObject.get("permCode");
        String permType = (String) searchObject.get("permType");
        String permName = (String) searchObject.get("permName");

        Example example = new Example(PermissionEntity.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("isDeleted", 0);
        if (!StringUtils.isEmpty(permCode)) {
            criteria.andLike("permCode", "%" + permCode + "%");
        }
        if (!StringUtils.isEmpty(permType)) {
            criteria.andEqualTo("permType", permType);
        }
        if (!StringUtils.isEmpty(permName)) {
            criteria.andLike("permName", "%" + permName + "%");
        }

        //开始查询
        List<PermissionEntity> permissionEntities = permissionMapper.selectByExample(example);
        PageInfo<PermissionEntity> pageInfo = new PageInfo<PermissionEntity>(permissionEntities);

        //转换VO
        List<PermissionVo> userVos = BeanCopyUtils.getCopyList(pageInfo.getList(), PermissionVo.class);

        return new ResponsePageDto(userVos, pageInfo.getTotal(), pageInfo.getPageSize(), pageInfo.getPageNum());
    }


    /**
     * 创建权限
     *
     * @param permDto
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void createPermission(PermDto permDto) throws Exception {
//
        //判断权限重复
        Example example = new Example(PermissionEntity.class);
        example.createCriteria().andEqualTo("permCode", permDto.getPermCode());
        PermissionEntity permissionEntity = permissionMapper.selectOneByExample(example);
        if (permissionEntity != null) {
            throw new CustomException("权限编码重复");
        }

        //转换Entity
        PermissionEntity entity = permDto.getEntity(PermissionEntity.class);
        entity.setGmtCreate(new Date());
        entity.setIsDeleted(0);

        //添加权限
        permissionMapper.insert(entity);
    }

    /**
     * 判断权限是否唯一
     *
     * @param permDto
     */
    public void checkPermission(PermDto permDto) {
        //判断角色代码是否重复
        Example example = new Example(PermissionEntity.class);
        example.createCriteria().andEqualTo("permCode", permDto.getPermCode())
                .andEqualTo("isDeleted", 0);

        PermissionEntity permissionEntity = permissionMapper.selectOneByExample(example);
        if (permissionEntity != null && !permissionEntity.getId().equals(permDto.getId())) {
            throw new CustomException("角色代码重复");
        }
    }

    /**
     * 更改权限
     *
     * @param permDto
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updatePermission(PermDto permDto) throws Exception{
        //判断权限是否存在
        PermissionEntity permissionEntity = permissionMapper.selectByPrimaryKey(permDto.getId());
        if (permissionEntity == null) {
            throw new CustomException("查无此权限");
        }
        //判断权限是否唯一
        checkPermission(permDto);

        //转换实体类属性注入
        PermissionEntity entity = permDto.getEntity(PermissionEntity.class);
        entity.setGmtModified(new Date());

        System.out.println("entity: "+entity.getGmtCreate());
        //更新权限
        permissionMapper.updateByPrimaryKey(entity);
    }

    /**
     * 删除权限
     *
     * @param id
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delPermission(String id) {

        //判断权限是否存在
        PermissionEntity permissionEntity = permissionMapper.selectByPrimaryKey(id);
        if (permissionEntity == null) {
            throw new CustomException("该权限不存在");
        }

        //删除权限
        permissionEntity.setIsDeleted(1);
        permissionEntity.setGmtModified(new Date());
        permissionMapper.updateByPrimaryKeySelective(permissionEntity);

        //删除关联表
        Example example = new Example(RolePermissionEntity.class);
        example.createCriteria().andEqualTo("permId",permissionEntity.getId());
        rolesPermissionMapper.deleteByExample(example);
    }

    /**
     * @param permId
     */
    @Override
    public List<PermPathVo> queryPermUris(String permId) throws Exception{
        //查询条件
        Example example = new Example(RolePermissionEntity.class);
        example.createCriteria().andEqualTo("isDeleted",0)
                .andEqualTo("permId",permId);

        List<PermRequestPathEntity> permRequestPathEntities = permRequestPathMapper.selectByExample(example);

        //转换VO
        List<PermPathVo> copyList = BeanCopyUtils.getCopyList(permRequestPathEntities, PermPathVo.class);

        return copyList;
    }


    /**
     * 权限url设置
     *
     * @param permRequestPathDto
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void assignPermsUrl(PermRequestPathDto permRequestPathDto) {
        PermissionEntity permissionEntity = permissionMapper.selectByPrimaryKey(permRequestPathDto.getPermId());
        if (permissionEntity == null) {
            throw new CustomException("查无该权限");
        }

        //删除关联表数据
        Example example = new Example(PermRequestPathEntity.class);
        example.createCriteria().andEqualTo("permId",permRequestPathDto.getPermId());
        permRequestPathMapper.deleteByExample(example);

        //转换String数组

        String[] urlIds = permRequestPathDto.getUriIds().split(",");
        System.out.println("urlIds: "+urlIds);
        //添加权限数据
        for(String urlId:urlIds){
            //权限Url属性注入
            PermRequestPathEntity permRequestPathEntity = new PermRequestPathEntity();
            permRequestPathEntity.setPermId(permRequestPathDto.getPermId());
            permRequestPathEntity.setRequestPathId(urlId);
            permRequestPathEntity.setGmtCreate(new Date());
            permRequestPathEntity.setGmtModified(new Date());
            permRequestPathEntity.setIsDeleted(0);

            //执行插入操作
            permRequestPathMapper.insert(permRequestPathEntity);
        }
    }

    /**
     * 查询所有权限
     *
     * @return
     */
    @Override
    public List<PermissionEntity> queryUserList() {
        //查询条件
        Example example = new Example(UserEntity.class);
        example.createCriteria().andEqualTo("isDeleted",0);

        //执行查询
        List<PermissionEntity> permissionEntities = permissionMapper.selectByExample(example);

        return permissionEntities;
    }
}

