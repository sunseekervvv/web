package com.sunseeker.mall.seller.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sunseeker.common.utils.PageUtils;
import com.sunseeker.mall.seller.entity.SysUserEntity;

import java.util.Map;

/**
 * 系统用户
 */
public interface SysUserService extends IService<SysUserEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void saveUser(SysUserEntity sysUser);
}

