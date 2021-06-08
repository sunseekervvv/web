package com.sunseeker.mall.seller.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sunseeker.common.utils.PageUtils;
import com.sunseeker.mall.seller.entity.SysUserRoleEntity;

import java.util.List;
import java.util.Map;

/**
 */
public interface SysUserRoleService extends IService<SysUserRoleEntity> {

    PageUtils queryPage(Map<String, Object> params);
    void saveOrUpdate(Long userId, List<Long> roleIdList);
}

