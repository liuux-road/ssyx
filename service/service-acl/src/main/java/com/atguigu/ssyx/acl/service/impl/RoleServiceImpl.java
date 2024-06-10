package com.atguigu.ssyx.acl.service.impl;

import com.atguigu.ssyx.acl.mapper.RoleMapper;
import com.atguigu.ssyx.acl.service.AdminRoleService;
import com.atguigu.ssyx.acl.service.RoleService;
import com.atguigu.ssyx.model.acl.AdminRole;
import com.atguigu.ssyx.model.acl.Role;
import com.atguigu.ssyx.vo.acl.RoleQueryVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * ClassName: RoleServiceImpl
 * Package: com.atguigu.ssyx.acl.service.impl
 * Description:
 *
 * @Author liuux
 * @Create 2024/1/16 18:08
 * @Version 1.0
 */

@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Autowired
    private AdminRoleService adminRoleService;


    // 1 角色列表（条件分页查询）
    @Override
    public IPage<Role> selectRolePage(Page<Role> pageParam,
                                      RoleQueryVo roleQueryVo) {
        //获取条件值
        String roleName = roleQueryVo.getRoleName();
        //创建mp条件对象
        LambdaQueryWrapper<Role> wrapper = new LambdaQueryWrapper<>();
        //判断条件值是否为空，封装查询条件
        if(!StringUtils.isEmpty(roleName)) {
            wrapper.like(Role::getRoleName, roleName);
        }
        //调用方法实现分页查询
        IPage<Role> roleIModel = baseMapper.selectPage(pageParam, wrapper);
        //返回分页对象
        return roleIModel;
    }


    // 为用户分配角色，查看信息
    @Override
    public Map<String, Object> getRoleByAdminId(Long adminId) {

        // 查询所有角色
        List<Role> allRoleList = baseMapper.selectList(null);

        Map<String, Object> result = new HashMap<>();
        result.put("allRolesList", allRoleList);

        // 根据用户id查询用户分配的角色列表
        // 1. 根据用户id进行查询，查询用户角色关系表admin_role 查询用户分配的id列表，这一步骤得到List<AdminRole>
        LambdaQueryWrapper<AdminRole> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(AdminRole::getAdminId, adminId); // 设置查询条件，根据用户id adminId
        List<AdminRole> adminRoleList = adminRoleService.list(wrapper);
        // 2. 上一步取到AdminRole的列表，但是我们需要Long类型，也就是角色id的列表，这里进行转换
        List<Long> roleList = adminRoleList.stream().
                map(item->item.getRoleId()).
                collect(Collectors.toList());
        // 3. 现在得到了Roleid，转化为Role对象集合
        List<Role> roleList_ans = new ArrayList<>();
        for(Role role:allRoleList)
            if (roleList.contains(role.getId()))
                roleList_ans.add(role);

        result.put("assignRoles", roleList_ans);

        return result;
    }


    // 为用户分配角色，分配角色
    @Override
    public void saveAdminRole(Long adminId, Long[] roleIds) {
        // 删除旧的
//        QueryWrapper<AdminRole> wrapper = new QueryWrapper<>();
//        wrapper.eq("admin_id", adminId);
//        adminRoleService.remove(wrapper);
        adminRoleService.remove(new QueryWrapper<AdminRole>().eq("admin_id", adminId));


        // 增加新的
        List<AdminRole> adminRoleList = new ArrayList<>();
        for (Long roleId : roleIds) {
            AdminRole adminRole = new AdminRole();
            adminRole.setAdminId(adminId);
            adminRole.setRoleId(roleId);
            adminRoleList.add(adminRole);
        }
        adminRoleService.saveBatch(adminRoleList);
    }


}