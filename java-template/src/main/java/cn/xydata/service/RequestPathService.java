package cn.xydata.service;

import cn.xydata.common.dto.QueryParamsDto;
import cn.xydata.common.dto.ResponsePageDto;
import cn.xydata.dto.RequestPathDto;
import cn.xydata.entity.RequestPathEntity;

import java.util.List;

/**
 * @Author: haojie
 * @qq :1422471205
 * @CreateTime: 2021-07-04-13-23
 */
public interface RequestPathService {

    /**
     * 查询且不分页
     * @return
     */
    List<RequestPathEntity> queryRequestPathList();

    /**
     * 分页查询
     * @param paramsDto
     * @return
     * @throws Exception
     */
    ResponsePageDto queryUserPage(QueryParamsDto paramsDto) throws Exception;

    /**
     * 创建URL
     * @param requestPathDto
     * @throws Exception
     */
    void createUrl(RequestPathDto requestPathDto) throws Exception;

    /**
     * 更改URL
     * @param requestPathDto
     */
    void updateURL(RequestPathDto requestPathDto);

    /**
     * 删除URL
     * @param requestPathDto
     */
    void delUrl(RequestPathDto requestPathDto);
}
