package com.sunseeker.mall.order.dao;

import com.sunseeker.mall.order.entity.OrderItemEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单项信息
 * 
 * @author sunseeker
 * @email 1522292372@qq.com
 * @date 2021-03-23 23:03:00
 */
@Mapper
public interface OrderItemDao extends BaseMapper<OrderItemEntity> {
	
}
