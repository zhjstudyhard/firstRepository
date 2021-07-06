package cn.xydata.api;

import cn.xydata.common.annotation.Error;
import cn.xydata.common.annotation.SysControllerLogger;
import cn.xydata.common.dto.QueryParamsDto;
import cn.xydata.common.exception.CustomException;
import cn.xydata.dto.PermDto;
import cn.xydata.dto.RequestPathDto;
import cn.xydata.dto.RoleDto;
import cn.xydata.service.RequestPathService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @Author: haojie
 * @qq :1422471205
 * @CreateTime: 2021-07-04-13-22
 */
@RestController
@RequestMapping("/api/aaa/path")
public class RequestPathApi {
    @Autowired
    private RequestPathService requestPathService;

    /**
     * @description: 查询权限路径并且不分页
     * @Param: []
     * @Return
     * @Date: 2020/10/26 9:48
     */
    @Error
    @RequestMapping(value = "/listNotPage", method = RequestMethod.POST)
    public Object queryRequestPathList() throws Exception {
        return requestPathService.queryRequestPathList();
    }

    /**
     * 用户信息分页
     *
     * @param paramsDto
     * @return
     */
    @SysControllerLogger(description = "分页查询用户")
    @Error
    @RequestMapping(value = "/list", method = {RequestMethod.POST})
    public Object queryUserPage(@RequestBody QueryParamsDto paramsDto) throws Exception {

        return requestPathService.queryUserPage(paramsDto);
    }

    /**
     * 创建URL
     *
     * @return
     */
    @SysControllerLogger(description = "创建角色")
    @Error
    @RequestMapping(value = "/createUrl", method = {RequestMethod.POST})
    public Object createUrl(@Valid @RequestBody RequestPathDto requestPathDto) throws Exception {
        requestPathService.createUrl(requestPathDto);
        return null;
    }

    /**
     * 更改URL
     * @param requestPathDto
     * @return
     * @throws Exception
     */
    @SysControllerLogger(description = "更改URL")
    @Error
    @RequestMapping(value = "/updateURL", method = RequestMethod.POST)
    public Object updateURL(@Validated @RequestBody RequestPathDto requestPathDto) throws Exception{
        //检查所有字段是否验证通过
        if (StringUtils.isEmpty(requestPathDto.getId())){
            throw new CustomException("URL的id不能为空");
        }
        requestPathService.updateURL(requestPathDto);
        return null;
    }

    /**
     * 删除URL
     * @param requestPathDto
     * @return
     */
    @SysControllerLogger(description = "删除URL")
    @Error
    @RequestMapping(value = "/delUrl", method = {RequestMethod.POST})
    public Object delUrl(@RequestBody RequestPathDto requestPathDto) {
        //校验权限id是否为空
        if (StringUtils.isEmpty(requestPathDto.getId())){
            throw new CustomException("URL的id不能为空");
        }
        requestPathService.delUrl(requestPathDto);
        return null;
    }

}
