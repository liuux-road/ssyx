package com.atguigu.ssyx.acl.controller;

import com.atguigu.ssyx.acl.service.PermissionService;
import com.atguigu.ssyx.common.result.Result;
import com.atguigu.ssyx.model.acl.Permission;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * ClassName: PermissionController
 * Package: com.atguigu.ssyx.acl.controller
 * Description:
 *
 * @Author liuux
 * @Create 2024/3/4 8:47
 * @Version 1.0
 */
@RestController
@RequestMapping("/admin/acl/permission")
 
@Api(tags="菜单管理")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @ApiOperation("获取菜单")
    @GetMapping()
    public Result getPermissionList() {
        List<Permission> list = permissionService.queryAllMenu();
        return Result.ok(list);
    }

    @ApiOperation("新增菜单")
    @PostMapping("save")
    public Result save(@RequestBody Permission permission) {
        boolean is_success = permissionService.save(permission);
        if(is_success) {
            return Result.ok(null);
        }
        return Result.fail(null);
    }

    @ApiOperation("修改菜单")
    @PutMapping("update")
    public Result updateById(@RequestBody Permission permission) {
        boolean is_success = permissionService.updateById(permission);
        if(is_success) {
            return Result.ok(null);
        }
        return Result.fail(null);
    }

    @ApiOperation("递归删除菜单")
    @DeleteMapping("remove/{id}")
    public Result remove(@PathVariable Long id) {
        boolean is_success = permissionService.removeChildById(id);
        if(is_success) {
            return Result.ok(null);
        }
        return Result.fail(null);
    }

    @ApiOperation("查看某个角色的权限列表")
    @GetMapping("toAssign/{roleId}")
    public Result toAssign(@PathVariable Long roleId) {
        Map<String, Object> map = permissionService.getPermissionByRoleId(roleId);
        return Result.ok(map);
    }

    @ApiOperation("给某个角色授权")
    @PostMapping("doAssign")
    public Result doAssign(@RequestParam Long roleId,
                           @RequestParam Long[] permissionId) {
        permissionService.updateChildById(roleId, permissionId);
        return Result.ok(null);
    }


}
