package com.atguigu.ssyx.acl.controller;

import com.atguigu.ssyx.acl.service.PermissionService;
import com.atguigu.ssyx.acl.service.RoleService;
import com.atguigu.ssyx.common.result.Result;
import com.atguigu.ssyx.model.acl.Permission;
import com.atguigu.ssyx.model.acl.Role;
import com.atguigu.ssyx.vo.acl.RoleQueryVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * ClassName: RoleController
 * Package: com.atguigu.ssyx.acl.controller
 * Description:
 *
 * @Author liuux
 * @Create 2024/1/16 18:04
 * @Version 1.0
 */
@Api(tags = "角色接口")
@RestController
@RequestMapping("admin/acl/role")
 
public class RoleController {

    @Autowired
    private RoleService roleService;

    // 1 角色列表（条件分页查询）
    @ApiOperation("角色条件分页查询")
    @GetMapping("{current}/{limit}")
    public Result pageList(@PathVariable Long current,
                           @PathVariable Long limit,
                           RoleQueryVo roleQueryVo) {
        // 上面注入，下面使用条件分页查询
        // 1 创建page对象，传递当前页和每页记录数
        Page<Role> pageParam = new Page<>(current, limit);

        // 2 调用service方法实现条件分页查询，返回分页对象
        IPage<Role> pageModel = roleService.selectRolePage(pageParam, roleQueryVo);

        return Result.ok(pageModel);
    }

    // 2 根据ID查询角色
    @ApiOperation("根据id查询角色")
    @GetMapping("get/{id}")
    public Result get(@PathVariable Long id) {
        Role role = roleService.getById(id);
        return Result.ok(role);
    }

    // 3 添加角色
    @ApiOperation("添加角色")
    @PostMapping("save")
    public Result save(@RequestBody Role role) { // 这里是json格式传递，@requestbody表示接受json数据，并且封装到对象中
        boolean is_success = roleService.save(role);
        if (is_success) {
            return Result.ok(null);
        }
        return Result.fail(null);
    }

    // 4 修改角色
    @ApiOperation("修改角色")
    @PutMapping("update")
    public Result update(@RequestBody Role role) {
        boolean is_success = roleService.updateById(role);
        if (is_success) {
            return Result.ok(null);
        }
        return Result.fail(null);
    }

    // 5 根据id删除角色
    @ApiOperation("根据id删除角色")
    @DeleteMapping("remove/{id}")
    public Result remove(@PathVariable Long id) {
        boolean is_success = roleService.removeById(id);
        if (is_success) {
            return Result.ok(null);
        }
        return Result.fail(null);
    }

    // 6 批量删除角色
    @ApiOperation("批量删除多个角色")
    @DeleteMapping("batchRemove")
    public Result removeRoles(@RequestBody List<Long> role_ids) { // json的数组格式对应java中的List集合
        boolean is_success = roleService.removeByIds(role_ids);
        if (is_success) {
            return Result.ok(null);
        }
        return Result.fail(null);
    }





}