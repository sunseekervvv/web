package com.sunseeker.mall.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sunseeker.common.to.SkuReductionTo;
import com.sunseeker.common.utils.PageUtils;
import com.sunseeker.mall.coupon.entity.SkuFullReductionEntity;

import java.util.Map;

/**
 * 商品满减信息
 *
 * @author leifengyang
 * @email leifengyang@gmail.com
 * @date 2019-10-08 09:36:40
 */
public interface SkuFullReductionService extends IService<SkuFullReductionEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void saveSkuReduction(SkuReductionTo reductionTo);


}

