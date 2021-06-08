package com.sunseeker.mall.seller.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sunseeker.mall.seller.entity.SysUserRoleEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户与角色对应关系
 */
@Mapper
public interface SysUserRoleDao extends BaseMapper<SysUserRoleEntity> {
	
}
