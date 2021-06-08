package com.sunseeker.mall.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sunseeker.common.utils.PageUtils;
import com.sunseeker.mall.order.entity.OrderSettingEntity;

import java.util.Map;

/**
 * 订单配置信息
 */
public interface OrderSettingService extends IService<OrderSettingEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

