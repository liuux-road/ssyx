package com.atguigu.ssyx.acl.service;

import com.atguigu.ssyx.model.acl.Permission;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * ClassName: PermissionService
 * Package: com.atguigu.ssyx.acl.service
 * Description:
 *
 * @Author liuux
 * @Create 2024/3/4 8:48
 * @Version 1.0
 */
public interface PermissionService extends IService<Permission> {
    boolean removeChildById(Long id);

    List<Permission> queryAllMenu();


    Map<String, Object> getPermissionByRoleId(Long roleId);

    void updateChildById(Long roleId, Long[] permissionId);
}
