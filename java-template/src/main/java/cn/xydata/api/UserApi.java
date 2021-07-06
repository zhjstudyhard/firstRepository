package cn.xydata.api;

import cn.xydata.common.dto.QueryParamsDto;
import cn.xydata.common.exception.CustomException;
import cn.xydata.dto.UserDto;
import cn.xydata.dto.UserRoleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import cn.xydata.common.annotation.Error;
import cn.xydata.common.annotation.SysControllerLogger;
import cn.xydata.common.controller.BaseController;
import cn.xydata.service.UserService;

import javax.validation.Valid;

/**
 * This is a class comment
 *
 * @author zhouky
 * @CreateDate 2020/07/01
 */
@RestController
@RequestMapping("/api/template")
public class UserApi extends BaseController {

    @Autowired
    private UserService userService;

    /**
     * 用户注册
     *
     * @param userDto
     * @return
     * @throws Exception
     */
    @SysControllerLogger(description = "用户注册")
    @Error
    @RequestMapping(value = "/createUser", method = {RequestMethod.POST})
    public Object createUser(@Valid @RequestBody UserDto userDto) throws Exception {
        userService.createUser(userDto);
        return null;
    }

    /**
     * 查找所有的数据且不需要分页
     * @return
     * @throws Exception
     */
    @Error
    @RequestMapping(value = "/listNotPage", method = RequestMethod.POST)
    public Object queryUserList() throws Exception {
        return userService.queryUserList();
    }

    /**
     * 用户信息分页
     *
     * @param paramsDto
     * @return
     */
    @SysControllerLogger(description = "分页查询用户")
    @Error
    @RequestMapping(value = "/queryUserPage", method = {RequestMethod.POST})
    public Object queryUserPage(@RequestBody QueryParamsDto paramsDto) throws Exception {

        return userService.queryUserPage(paramsDto);
    }

    /**
     * 更新用户信息
     *
     * @param userDto
     * @return
     */
    @SysControllerLogger(description = "更新用户信息")
    @Error
    @RequestMapping(value = "/updateUser", method = {RequestMethod.POST})
    public Object updateUser(@Valid @RequestBody UserDto userDto) throws Exception {
        userService.updateUser(userDto);
        return null;
    }

    /**
     * 锁定用户
     *
     * @param userDto
     * @return
     */
    @SysControllerLogger(description = "锁定用户")
    @Error
    @PostMapping("/updateUserLockById")
    public Object updateUserLockById(@RequestBody UserDto userDto) throws Exception {
        if (userDto.getId() == null) {
            throw new CustomException("用户id不能为空");
        }
        userService.updateUserLockById(userDto.getId());
        return null;
    }

    /**
     * 解锁用户
     *
     * @param userDto
     * @return
     */
    @SysControllerLogger(description = "解锁用户")
    @Error
    @RequestMapping(value = "/unlockUser", method = {RequestMethod.POST})
    public Object unlockUser(@RequestBody UserDto userDto) throws Exception{
        if (userDto.getId() == null) {
            throw new CustomException("用户id不能为空");
        }
        userService.unlockUser(userDto.getId());
        return null;
    }

    /**
     * 停用用户
     *
     * @param userDto
     * @return
     */
    @SysControllerLogger(description = "停用用户")
    @Error
    @RequestMapping(value = "/disabledUser", method = RequestMethod.POST)
    public Object disabledUser(@RequestBody UserDto userDto){
        if (userDto.getId() == null) {
            throw new CustomException("用户id不能为空");
        }
        userService.disabledUser(userDto.getId());
        return null;
    }

    /**
     * 启用用户
     *
     * @param userDto
     * @return
     */
    @SysControllerLogger(description = "启用用户")
    @Error
    @PostMapping("/enabledUser")
    public Object enabledUser(@RequestBody UserDto userDto){
        if (userDto.getId() == null) {
            throw new CustomException("用户id不能为空");
        }
        userService.enabledUser(userDto.getId());
        return null;
    }

    /**
     * 授予角色
     * @param userRoleDto
     * @return
     */
    @SysControllerLogger(description = "授予角色")
    @Error
    @PostMapping("/assignRoles")
    public Object assignRoles(@Validated @RequestBody UserRoleDto userRoleDto) {
        System.out.println("userRoleDto___________: "+userRoleDto);
        userService.assign(userRoleDto);
        return null;
    }

    /**
     * 删除用户
     * @param userDto
     * @return
     */
    @SysControllerLogger(description = "删除用户")
    @Error
    @PostMapping("/deleteUser")
    public Object deleteUser(@RequestBody UserDto userDto) {
        if (userDto.getId() == null) {
            throw new CustomException("用户id不能为空");
        }
        userService.deleteUser(userDto.getId());
        return null;
    }

    /**
     * 查询用户角色
     * @param userDto
     * @return
     */
    @SysControllerLogger(description = "查询用户角色")
    @Error
    @PostMapping("/userRoles")
    public Object userRoles(@RequestBody UserDto userDto) throws Exception{
        if (userDto.getId() == null) {
            throw new CustomException("用户id不能为空");
        }
        return userService.userRoles(userDto.getId());

    }
}
