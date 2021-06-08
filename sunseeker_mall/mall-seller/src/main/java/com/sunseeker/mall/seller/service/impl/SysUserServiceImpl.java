package com.sunseeker.mall.seller.service.impl;

import com.sunseeker.common.utils.PageUtils;
import com.sunseeker.common.utils.Query;
import com.sunseeker.mall.seller.dao.SysUserDao;
import com.sunseeker.mall.seller.entity.SysUserEntity;
import com.sunseeker.mall.seller.service.SysUserRoleService;
import com.sunseeker.mall.seller.service.SysUserService;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;



@Service("sysUserService")
public class SysUserServiceImpl extends ServiceImpl<SysUserDao, SysUserEntity> implements SysUserService {

    @Autowired
    private SysUserRoleService sysUserRoleService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SysUserEntity> page = this.page(
                new Query<SysUserEntity>().getPage(params),
                new QueryWrapper<SysUserEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public void saveUser(SysUserEntity sysUser) {
        sysUser.setCreateTime(new Date());
        sysUser.setCreateUserId(1L);
        sysUser.setStatus(1);
        //sha256加密
        String salt = RandomStringUtils.randomAlphanumeric(20);
        sysUser.setPassword(new Sha256Hash(sysUser.getPassword(), salt).toHex());
        sysUser.setSalt(salt);
        this.save(sysUser);
        ArrayList<Long> roleIdList = new ArrayList<>();
        roleIdList.add(2L);

        sysUser.setRoleIdList(roleIdList);

        //保存用户与角色关系
        sysUserRoleService.saveOrUpdate(sysUser.getUserId(), sysUser.getRoleIdList());
    }

}