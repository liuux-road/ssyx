package com.atguigu.ssyx.acl.controller;

import com.atguigu.ssyx.acl.service.AdminRoleService;
import com.atguigu.ssyx.acl.service.AdminService;
import com.atguigu.ssyx.acl.service.RoleService;
import com.atguigu.ssyx.common.result.Result;
import com.atguigu.ssyx.common.utils.MD5;
import com.atguigu.ssyx.model.acl.Admin;
import com.atguigu.ssyx.vo.acl.AdminQueryVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * ClassName: AdminController
 * Package: com.atguigu.ssyx.acl.controller
 * Description:
 *
 * @Author liuux
 * @Create 2024/3/3 14:34
 * @Version 1.0
 */

@Api(tags = "用户接口")
@RestController
 
@RequestMapping("/admin/acl/user")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @ApiOperation("获取用户分页列表")
    @GetMapping("{page}/{limit}")
    public Result getPageList(@PathVariable Long page,
                              @PathVariable Long limit,
                              AdminQueryVo adminQueryVo) {
        Page<Admin> pageParam = new Page<>(page, limit);
        IPage<Admin> pagemodel = adminService.selectPage(pageParam, adminQueryVo);
        return Result.ok(pagemodel);
    }

    @ApiOperation("获取管理用户")
    @GetMapping("get/{id}")
    public Result getByid(@PathVariable Long id) {
        Admin admin = adminService.getById(id);
        return Result.ok(admin);
    }

    @ApiOperation("新增管理用户")
    @PostMapping("save")
    public Result add(@RequestBody Admin admin) {
        admin.setPassword(MD5.encrypt(admin.getPassword())); // MD5加密
        boolean is_success = adminService.save(admin);
        if (is_success) {
            return Result.ok(null);
        }
        return Result.fail(null);
    }

    @ApiOperation("修改管理用户")
    @PutMapping("update")
    public Result update(@RequestBody Admin admin) {
        boolean is_success = adminService.updateById(admin);
        if (is_success) {
            return Result.ok(null);
        }
        return Result.fail(null);
    }


    @ApiOperation("删除管理用户")
    @DeleteMapping("remove/{id}")
    public Result removeByid(@PathVariable Long id) {
        boolean is_success = adminService.removeById(id);
        if (is_success) {
            return Result.ok(null);
        }
        return Result.fail(null);
    }


    @ApiOperation("根据id列表删除管理用户")
    @DeleteMapping("batchRemove")
    public Result removeByid(@RequestBody List<Long> idList) {
        boolean is_success = adminService.removeByIds(idList);
        if (is_success) {
            return Result.ok(null);
        }
        return Result.fail(null);
    }


    @Autowired
    private  RoleService roleService;

    @ApiOperation(value = "根据用户获取角色数据")
    @GetMapping("toAssign/{adminId}")
    public Result toAssign(@PathVariable Long adminId) {
        // 返回的map集合中包含两部分：所有角色、为用户分配的角色
        Map<String, Object> map = roleService.getRoleByAdminId(adminId);
        return Result.ok(map);
    }

    @ApiOperation(value = "根据用户分配角色")
    @PostMapping("doAssign")
    public Result doAssign(@RequestParam Long adminId,
                           @RequestParam Long[] roleId) {
        roleService.saveAdminRole(adminId, roleId);
        return Result.ok(null);
    }



}
