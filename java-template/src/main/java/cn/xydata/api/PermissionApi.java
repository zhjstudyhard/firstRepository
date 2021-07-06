package cn.xydata.api;

import cn.xydata.common.annotation.Error;
import cn.xydata.common.annotation.SysControllerLogger;
import cn.xydata.common.dto.QueryParamsDto;
import cn.xydata.common.exception.CustomException;
import cn.xydata.dto.PermDto;
import cn.xydata.dto.PermRequestPathDto;
import cn.xydata.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @Author: haojie
 * @qq :1422471205
 * @CreateTime: 2021-07-01-14-51
 */

@RestController
@RequestMapping("/api/permission")
public class PermissionApi {

    @Autowired
    private PermissionService permissionService;


    /**
     * 查找所有的数据且不需要分页
     * @return
     * @throws Exception
     */
    @Error
    @RequestMapping(value = "/listNotPage", method = RequestMethod.POST)
    public Object queryUserList() throws Exception {
        return permissionService.queryUserList();
    }
    /**
     * 分页查询
     * @return
     */
    @SysControllerLogger(description = "分页查询")
    @Error
    @RequestMapping(value = "/queryPermsPage", method = {RequestMethod.POST})
    public Object queryPermsPage(@RequestBody QueryParamsDto queryParamsDto) throws Exception{

        return permissionService.queryPermsPage(queryParamsDto);
    }


    /**
     * 创建权限
     * @param permDto
     * @return
     */
    @SysControllerLogger(description = "创建权限")
    @Error
    @RequestMapping(value = "/createPermission", method = RequestMethod.POST)
    public Object createPermission(@Valid @RequestBody PermDto permDto) throws Exception{
        System.out.println("permDto: "+permDto);
        permissionService.createPermission(permDto);
        return null;
    }

    /**
     * 更改权限
     * @param permDto
     * @return
     */
    @SysControllerLogger(description = "更改权限")
    @Error
    @RequestMapping(value = "/updatePermission", method = RequestMethod.POST)
    public Object updatePermission(@Validated @RequestBody PermDto permDto) throws Exception{
        //检查所有字段是否验证通过
        System.out.println("permDto: "+permDto);
        if (StringUtils.isEmpty(permDto.getId())){
            throw new CustomException("权限id不能为空");
        }
        permissionService.updatePermission(permDto);
        return null;
    }

    /**
     * 删除权限
     * @param permDto
     * @return
     */
    @SysControllerLogger(description = "删除权限")
    @Error
    @RequestMapping(value = "/delPermission", method = RequestMethod.POST)
    public Object delPermission(@RequestBody PermDto permDto) {
        //校验权限id是否为空
        if (StringUtils.isEmpty(permDto.getId())){
            throw new CustomException("权限id不能为空");
        }
        permissionService.delPermission(permDto.getId());
        return null;
    }

    /**
     * 权限请求列表
     * @param permRequestPathDto
     * @return
     */
    @SysControllerLogger(description = "权限请求列表")
    @Error
    @RequestMapping(value = "/queryPermUris", method = RequestMethod.POST)
    public Object queryPermUris(@RequestBody PermRequestPathDto permRequestPathDto) throws Exception {
        //校验权限id是否为空
        if (StringUtils.isEmpty(permRequestPathDto.getPermId())){
            throw new CustomException("权限id不能为空");
        }

        return  permissionService.queryPermUris(permRequestPathDto.getPermId());
    }

    /**
     * 权限url设置
     * @return
     */
    @SysControllerLogger(description = "权限url设置")
    @Error
    @RequestMapping(value = "/assignPermsUrl", method = {RequestMethod.POST})
    public Object assignPermsUrl(@RequestBody PermRequestPathDto permRequestPathDto) {
        if(StringUtils.isEmpty(permRequestPathDto.getPermId())){
            throw new CustomException("权限id不能为空");
        }
        //权限url设置
        permissionService.assignPermsUrl(permRequestPathDto);
        return null;
    }

}
