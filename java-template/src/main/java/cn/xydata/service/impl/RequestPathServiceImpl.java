package cn.xydata.service.impl;

import cn.xydata.common.dto.QueryParamsDto;
import cn.xydata.common.dto.ResponsePageDto;
import cn.xydata.common.exception.CustomException;
import cn.xydata.dto.RequestPathDto;
import cn.xydata.dto.RoleDto;
import cn.xydata.entity.PermRequestPathEntity;
import cn.xydata.entity.RequestPathEntity;
import cn.xydata.entity.RoleEntity;
import cn.xydata.entity.UserEntity;
import cn.xydata.mapper.PermRequestPathMapper;
import cn.xydata.mapper.RequestPathMapper;
import cn.xydata.service.RequestPathService;
import cn.xydata.utils.BeanCopyUtils;
import cn.xydata.vo.UserVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @Author: haojie
 * @qq :1422471205
 * @CreateTime: 2021-07-04-13-26
 */
@Service
public class RequestPathServiceImpl implements RequestPathService {
    @Autowired
    private RequestPathMapper requestPathMapper;

    @Autowired
    private PermRequestPathMapper permRequestPathMapper;

    /**
     * 查询且不分页
     *
     * @return
     */
    @Override
    public List<RequestPathEntity> queryRequestPathList() {
        //查询条件
        Example example = new Example(RequestPathEntity.class);
        example.createCriteria().andEqualTo("isDeleted", 0);

        //执行查询
        List<RequestPathEntity> requestPathEntities = requestPathMapper.selectByExample(example);

        return requestPathEntities;
    }

    /**
     * 分页查询
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
        String url = (String) searchObject.get("url");
        String description = (String) searchObject.get("description");

        Example example = new Example(RequestPathEntity.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("isDeleted", 0);
        if (!StringUtils.isEmpty(url)) {
            criteria.andLike("url", "%" + url + "%");
        }
        if (!StringUtils.isEmpty(description)) {
            criteria.andLike("description", "%" + description + "%");
        }

        //开始查询
        List<RequestPathEntity> requestPathEntities = requestPathMapper.selectByExample(example);
        PageInfo<RequestPathEntity> pageInfo = new PageInfo<>(requestPathEntities);

        //转换实体类
        List<RequestPathEntity> copyList = BeanCopyUtils.getCopyList(pageInfo.getList(), RequestPathEntity.class);

        return new ResponsePageDto(copyList, pageInfo.getTotal(), pageInfo.getPageSize(), pageInfo.getPageNum());
    }

    /**
     * 创建URL
     *
     * @param requestPathDto
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void createUrl(RequestPathDto requestPathDto) throws Exception {
        //判断URL编码是否重复
        Example example = new Example(RequestPathEntity.class);
        example.createCriteria().andEqualTo("isDeleted", 0)
                .andEqualTo("url", requestPathDto.getUrl());
        //执行查询
        if (requestPathMapper.selectCountByExample(example) > 0) {
            throw new CustomException("url编码重复");
        }

        //DTO转换实体类
        RequestPathEntity entity = requestPathDto.getEntity(RequestPathEntity.class);
        entity.setGmtCreate(new Date());
        //执行插入操作
        requestPathMapper.insert(entity);
    }

    /**
     * 判断URL编码是否唯一
     *
     * @param requestPathDto
     */
    public void checkURL(RequestPathDto requestPathDto) {
        //判断角色代码是否重复
        Example example = new Example(RequestPathEntity.class);
        example.createCriteria().andEqualTo("url", requestPathDto.getUrl())
                .andEqualTo("isDeleted", 0);
        RequestPathEntity requestPathEntity = requestPathMapper.selectOneByExample(example);
        if (requestPathEntity != null && !requestPathEntity.getId().equals(requestPathDto.getId())) {
            throw new CustomException("角色代码重复");
        }
    }

    /**
     * 更改URL
     *
     * @param requestPathDto
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateURL(RequestPathDto requestPathDto) {
        //判断URL是否删除
        RequestPathEntity requestPathEntity = requestPathMapper.selectByPrimaryKey(requestPathDto.getId());
        if (requestPathEntity == null) {
            throw new CustomException("URL不存在");
        }
        //更改校验URL是否唯一
        checkURL(requestPathDto);

        //转换实体类
        BeanUtils.copyProperties(requestPathDto, requestPathEntity);
        requestPathEntity.setGmtModified(new Date());

        //执行添加
        requestPathMapper.updateByPrimaryKeySelective(requestPathEntity);
    }

    /**
     * 删除URL
     *
     * @param requestPathDto
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delUrl(RequestPathDto requestPathDto) {
        //判断URL是否存在
        RequestPathEntity requestPathEntity = requestPathMapper.selectByPrimaryKey(requestPathDto.getId());
        if (requestPathEntity == null) {
            throw new CustomException("URL不存在");
        }

        //删除关联表数据
        Example example = new Example(PermRequestPathEntity.class);
        example.createCriteria().andEqualTo("isDeleted",0)
                .andEqualTo("requestPathId",requestPathDto.getId());
        //执行删除操作
        permRequestPathMapper.deleteByExample(example);

        //更新数据表
        requestPathEntity.setGmtModified(new Date());
        requestPathEntity.setIsDeleted(1);
        requestPathMapper.updateByPrimaryKeySelective(requestPathEntity);
    }
}
