package cn.xydata.service.impl;

import cn.xydata.common.dto.QueryParamsDto;
import cn.xydata.common.dto.ResponsePageDto;
import cn.xydata.constant.Constant;
import cn.xydata.dto.UserRoleDto;
import cn.xydata.entity.UserRoleEntity;
import cn.xydata.mapper.UserRoleMapper;
import cn.xydata.utils.BeanCopyUtils;
import cn.xydata.vo.UserRoleVo;
import cn.xydata.vo.UserVo;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import cn.xydata.common.exception.CustomException;
import cn.xydata.dto.UserDto;
import cn.xydata.entity.UserEntity;
import cn.xydata.mapper.RolesMapper;
import cn.xydata.mapper.UserMapper;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.xydata.service.UserService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * @author 86181
 */
@Service
public class UserServiceImpl implements UserService {
    private static Example example;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RolesMapper rolesMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public Object test(UserEntity userEntity) {
        return null;
    }

    /**
     * 注册用户
     *
     * @param userDto
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void createUser(UserDto userDto) throws Exception {

        //条件查询
        example = new Example(UserEntity.class);
        example.createCriteria().andEqualTo("username", userDto.getUsername())
                .andEqualTo("isDeleted",0);
        List<UserEntity> userEntities = userMapper.selectByExample(example);
        if (userEntities.size() > 0) {
            throw new CustomException("用户名已存在");
        }

        //注册用户
//        String id = UUID.randomUUID().toString().trim().replaceAll("-", "");
//        userDto.setId(id);
        UserEntity userEntity = userDto.getDto(UserEntity.class);
        System.out.println("userEntity: " + userEntity);
        userEntity.setIsDeleted(0);
        userEntity.setIsExpired(0);
        userEntity.setGmtCreate(new Date());
        //密码加密
        String password = DigestUtils.md5DigestAsHex(userEntity.getPassword().getBytes());
        userEntity.setPassword(password);
        userMapper.insert(userEntity);
    }


    /**
     * 分页查询用户
     *
     * @param paramsDto
     * @return
     */
    @Override
    public ResponsePageDto queryUserPage(QueryParamsDto paramsDto) throws Exception {
        //开启分页
        PageHelper.startPage(Integer.parseInt(paramsDto.getCurrent()), Integer.parseInt(paramsDto.getPageSize()));
        //构建查询条件
        HashMap<String, Object> searchObject = paramsDto.getSearchObject();
        String username = (String) searchObject.get("username");
        Integer locked = (Integer) searchObject.get("locked");
        Integer enabled = (Integer) searchObject.get("enabled");

        Example example = new Example(UserEntity.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("isDeleted",0);
        if (!StringUtils.isEmpty(username)) {
            criteria.andLike("username", "%" + username + "%");
        }
        if (locked != null) {
            criteria.andEqualTo("locked", locked);
        }
        if (enabled != null) {
            criteria.andEqualTo("enabled", enabled);
        }

        //开始查询
        List<UserEntity> userEntities = userMapper.selectByExample(example);
        PageInfo<UserEntity> pageInfo = new PageInfo<UserEntity>(userEntities);

        //转换VO
        List<UserVo> userVos = BeanCopyUtils.getCopyList(pageInfo.getList(), UserVo.class);

        return new ResponsePageDto(userVos, pageInfo.getTotal(), pageInfo.getPageSize(), pageInfo.getPageNum());
    }

    /**
     * 用户编辑或更新
     *
     * @param userDto
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateUser(UserDto userDto) throws Exception {
        UserEntity userEntity = userMapper.queryUserById(userDto.getId());
        if (userEntity == null) {
            throw new CustomException("查无此人");
        }
        BeanUtils.copyProperties(userDto, userEntity, Constant.IGNORE_FIELDS);
        userEntity.setGmtModified(new Date());
        userMapper.updateByPrimaryKey(userEntity);
    }

    /**
     * 锁定用户
     *
     * @param id
     */
    @Override
    public void updateUserLockById(String id) {
        if (userMapper.queryUserById(id) == null) {
            throw new CustomException("查无此人");
        }
        userMapper.updateUserLockById(id);
    }

    /**
     * 解锁用户
     *
     * @param id
     */
    @Override
    public void unlockUser(String id) {
        if (userMapper.queryUserById(id) == null) {
            throw new CustomException("查无此人");
        }
        userMapper.unlockUser(id);
    }

    /**
     * 停用用户
     *
     * @param id
     */
    @Override
    public void disabledUser(String id) {
        if (userMapper.queryUserById(id) == null) {
            throw new CustomException("查无此人");
        }
        userMapper.disabledUser(id);
    }

    /**
     * 启用用户
     *
     * @param id
     */
    @Override
    public void enabledUser(String id) {
        if (userMapper.queryUserById(id) == null) {
            throw new CustomException("查无此人");
        }
        userMapper.enabledUser(id);
    }

    /**
     * 授予角色
     *
     * @param userRoleDto
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void assign(UserRoleDto userRoleDto) {
        UserEntity userEntity = userMapper.selectByPrimaryKey(userRoleDto.getUserId());
        if (userEntity == null) {
            throw new CustomException("查无此人");
        }

        //删除关联表数据
        Example example = new Example(UserRoleEntity.class);
        example.createCriteria().andEqualTo("userId", userRoleDto.getUserId());
        userRoleMapper.deleteByExample(example);

        //遍历角色id
        List<String> roleIds = userRoleDto.getRoleIds();
        if (roleIds.size() > 0) {
            for (String roleId : roleIds) {
                UserRoleEntity userRoleEntity = new UserRoleEntity();
                userRoleEntity.setId(UUID.randomUUID().toString().trim().replaceAll("-", ""));
                userRoleEntity.setUserId(userRoleDto.getUserId());
                userRoleEntity.setRoleId(roleId);
                userRoleEntity.setGmtmodified(new Date());
                userRoleEntity.setIsDeleted(0);
                userRoleMapper.insert(userRoleEntity);
            }
        }


    }

    /**
     * 删除用户
     *
     * @param userId
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteUser(String userId) {
        //校验用户
        UserEntity userEntity = userMapper.selectByPrimaryKey(userId);
        if (userEntity == null) {
            throw new CustomException("查无此人");
        }

        userEntity.setIsDeleted(1);
        userEntity.setGmtModified(new Date());
        userMapper.updateByPrimaryKey(userEntity);

        //删除关联表
        Example example = new Example(UserRoleEntity.class);
        example.createCriteria().andEqualTo("userId", userId);
        userRoleMapper.deleteByExample(example);
    }

    /**
     * 查询用户角色
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public List<UserRoleVo> userRoles(String id) throws Exception {
        //查询条件
        Example example = new Example(UserRoleEntity.class);
        example.createCriteria().andEqualTo("userId",id);

        List<UserRoleEntity> userRoleEntities = userRoleMapper.selectByExample(example);

        //转换VO
        List<UserRoleVo> copyList = BeanCopyUtils.getCopyList(userRoleEntities, UserRoleVo.class);

        return copyList;
    }


    /**
     * 查询所有用户
     * @return
     */
    @Override
    public List<UserEntity> queryUserList() {
        //查询条件
        Example example = new Example(UserEntity.class);
        example.createCriteria().andEqualTo("isDeleted",0);

        //执行查询
        List<UserEntity> userEntities = userMapper.selectByExample(example);

        return userEntities;
    }
}