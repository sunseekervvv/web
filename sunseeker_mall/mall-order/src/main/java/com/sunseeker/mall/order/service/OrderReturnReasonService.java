package com.sunseeker.mall.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sunseeker.common.utils.PageUtils;
import com.sunseeker.mall.order.entity.OrderReturnReasonEntity;

import java.util.Map;

/**
 * ้่ดงๅๅ 
 */
public interface OrderReturnReasonService extends IService<OrderReturnReasonEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

