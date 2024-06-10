package com.atguigu.ssyx.acl.utils;

import com.atguigu.ssyx.model.acl.Permission;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: PermissionUtils
 * Package: com.atguigu.ssyx.acl.utils
 * Description:
 *
 * @Author liuux
 * @Create 2024/3/4 9:42
 * @Version 1.0
 */
public class PermissionUtils {
    public static List<Permission> buildPermissionList(List<Permission> allPermissionList) {
        List<Permission> ans = new ArrayList<>();

        for(Permission permission:allPermissionList) {
            if(permission.getPid()==0) {
                permission.setLevel(1);
                ans.add(doFindChildren(permission, allPermissionList));
            }
        }

        return ans;
    }

    private static Permission doFindChildren(Permission permission, List<Permission> allPermissionList) {

        permission.setChildren(new ArrayList<Permission>());

        for(Permission p:allPermissionList) {
            if(p.getPid()== permission.getId()) {
                p.setLevel(permission.getLevel()+1);
                if (permission.getChildren() == null){
                    permission.setChildren(new ArrayList<>());
                }
                permission.getChildren().add(doFindChildren(p, allPermissionList));
            }
        }

        return permission;
    }
}
