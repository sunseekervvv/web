package com.sunseeker.mall.seller.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sunseeker.mall.seller.entity.SysUserEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 系统用户
 */
@Mapper
public interface SysUserDao extends BaseMapper<SysUserEntity> {
	
}
