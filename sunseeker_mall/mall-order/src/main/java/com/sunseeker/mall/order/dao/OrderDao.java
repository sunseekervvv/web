package com.sunseeker.mall.order.dao;

import com.sunseeker.mall.order.entity.OrderEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 订单
 * 
 * @author sunseeker
 * @email 1522292372@qq.com
 * @date 2021-03-23 23:03:00
 */
@Mapper
public interface OrderDao extends BaseMapper<OrderEntity> {
    void updateOrderStatus(@Param("orderSn") String orderSn, @Param("code") Integer code, @Param("payType") Integer payType);
}
