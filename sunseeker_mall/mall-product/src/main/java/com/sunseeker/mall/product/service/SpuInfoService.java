package com.sunseeker.mall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sunseeker.common.utils.PageUtils;
import com.sunseeker.mall.product.entity.SpuInfoEntity;
import com.sunseeker.mall.product.vo.SpuSaveVo;

import java.util.Map;

/**
 * spu信息
 *
 * @author leifengyang
 * @email leifengyang@gmail.com
 * @date 2019-10-01 21:08:49
 */
public interface SpuInfoService extends IService<SpuInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void saveSpuInfo(SpuSaveVo vo);

    void saveBaseSpuInfo(SpuInfoEntity infoEntity);


    PageUtils queryPageByCondition(Map<String, Object> params);

    SpuInfoEntity getSpuBySkuId(Long skuId);

    // 商品上架
    void up(Long spuId);
}

