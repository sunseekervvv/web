package com.sunseeker.mall.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sunseeker.common.utils.PageUtils;
import com.sunseeker.mall.order.entity.OrderItemEntity;
import com.sunseeker.mall.order.vo.OrderItemConfirmVo;
import com.sunseeker.mall.order.vo.OrderSnVo;

import java.util.Map;

/**
 * 订单项信息
 */
public interface OrderItemService extends IService<OrderItemEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void updateOrderSn(OrderSnVo orderSn);

    void confirm(OrderItemConfirmVo orderItemConfirmVo);
}

