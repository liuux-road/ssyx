package com.atguigu.ssyx.acl.service.impl;

import com.atguigu.ssyx.acl.mapper.PermissionMapper;
import com.atguigu.ssyx.acl.service.PermissionService;
import com.atguigu.ssyx.acl.service.RolePermissionService;
import com.atguigu.ssyx.acl.utils.PermissionUtils;
import com.atguigu.ssyx.model.acl.Permission;
import com.atguigu.ssyx.model.acl.Role;
import com.atguigu.ssyx.model.acl.RolePermission;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * ClassName: PermissionServiceImpl
 * Package: com.atguigu.ssyx.acl.service.impl
 * Description:
 *
 * @Author liuux
 * @Create 2024/3/4 8:49
 * @Version 1.0
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {

    @Override
    public List<Permission> queryAllMenu() {

        List<Permission> allPermissionList= baseMapper.selectList(null);

        List<Permission> result = PermissionUtils.buildPermissionList(allPermissionList);

        return result;
    };

    //递归删除菜单
    @Override
    public boolean removeChildById(Long id) {
        List<Long> list = new ArrayList<>();

        // 递归加入到删除列表
        this.selectAllChildId(id, list);

        list.add(id);

        baseMapper.deleteBatchIds(list);

        return true;
    }

    /**
     *	递归获取子节点
     */
    private void selectAllChildId(Long id, List<Long> list) {
        List<Permission> childList = baseMapper.selectList(new LambdaQueryWrapper<Permission>().eq(Permission::getPid, id));
        childList.stream().forEach(item->{
            list.add(item.getId());
            this.selectAllChildId(item.getId(), list);
        });
    }


    @Autowired
    private RolePermissionService rolePermissionService;
    @Override
    public Map<String, Object> getPermissionByRoleId(Long roleId) {

        Map<String, Object> ans = new HashMap<>();
        // 查询所有角色
        List<Permission> allPermissionList = baseMapper.selectList(null);
        ans.put("allPermissionList", allPermissionList);

        // 查询已分配的角色
        // 1. 得到RolePermission列表
        List<RolePermission> rolePermissionsList = rolePermissionService.list(new LambdaQueryWrapper<RolePermission>().eq(RolePermission::getRoleId, roleId));

        // 2. 得到PermissionId列表
        List<Long> permissionIdList = rolePermissionsList.stream().
                map(item->item.getPermissionId()).
                collect(Collectors.toList());

        // 3. 得到Permission列表
        List<Permission> permissionList = new ArrayList<>();
        for (Permission permission:allPermissionList) {
            if (permissionIdList.contains(permission.getId())) {
                permissionList.add(permission);
            }
        }
        ans.put("assignPermissions", permissionList);

        return ans;
    };

    @Override
    public void updateChildById(Long roleId, Long[] permissionId) {

        rolePermissionService.remove(new LambdaQueryWrapper<RolePermission>().eq(RolePermission::getPermissionId, permissionId));

        List<RolePermission> list = new ArrayList<>();
        for (Long p : permissionId) {
            RolePermission rolePermission = new RolePermission();
            rolePermission.setPermissionId(p);
            rolePermission.setRoleId(roleId);
            list.add(rolePermission);
        }
        rolePermissionService.saveBatch(list);

    };
}
