package com.sunseeker.mall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sunseeker.common.utils.PageUtils;
import com.sunseeker.mall.product.entity.SkuInfoEntity;
import com.sunseeker.mall.product.vo.SkuItemVo;
import com.sunseeker.mall.product.vo.SkuSaleVo;

import java.util.List;
import java.util.Map;

/**
 * sku信息
 */
public interface SkuInfoService extends IService<SkuInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void saveSkuInfo(SkuInfoEntity skuInfoEntity);

    PageUtils queryPageByCondition(Map<String, Object> params);


    List<SkuInfoEntity> getSkusBySpuId(Long spuId);

    SkuItemVo item(Long skuId);

    void updateSale(SkuSaleVo skuSaleVo);

}

