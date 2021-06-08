package com.sunseeker.mall.coupon.dao;

import com.sunseeker.mall.coupon.entity.MemberPriceEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品会员价格
 * 
 * @author sunseeker
 * @email 1522292372@qq.com
 * @date 2021-03-23 22:34:47
 */
@Mapper
public interface MemberPriceDao extends BaseMapper<MemberPriceEntity> {
	
}
