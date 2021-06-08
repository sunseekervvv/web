package com.sunseeker.mall.coupon.dao;

import com.sunseeker.mall.coupon.entity.CouponEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 优惠券信息
 * 
 * @author sunseeker
 * @email 1522292372@qq.com
 * @date 2021-03-24 10:43:06
 */
@Mapper
public interface CouponDao extends BaseMapper<CouponEntity> {
	
}
