package com.atguigu.ssyx.acl.service.impl;

import com.atguigu.ssyx.model.acl.Admin;
import com.atguigu.ssyx.acl.mapper.AdminMapper;
import com.atguigu.ssyx.acl.service.AdminService;
import com.atguigu.ssyx.vo.acl.AdminQueryVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * ClassName: AdminServiceImp
 * Package: com.atguigu.ssyx.acl.service.impl
 * Description:
 *
 * @Author liuux
 * @Create 2024/3/3 14:36
 * @Version 1.0
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {
    @Override
    public IPage<Admin> selectPage(Page<Admin> pageParam, AdminQueryVo adminQueryVo) {
        //获取条件值：角色名称
        String adminName = adminQueryVo.getName();
        //创建条件构造器对象
        LambdaQueryWrapper<Admin> wrapper = new LambdaQueryWrapper<>();
        //判断条件值是否为空
        if(!StringUtils.isEmpty(adminName)) {
            //封装条件
            wrapper.like(Admin::getName,adminName);
        }
        //调用mapper方法实现条件分页查询
        IPage<Admin> pageModel = baseMapper.selectPage(pageParam, wrapper);
        return pageModel;
    }
}
