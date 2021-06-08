package com.sunseeker.mall.ware.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sunseeker.common.to.mq.OrderTo;
import com.sunseeker.common.to.mq.StockLockedTo;
import com.sunseeker.common.utils.PageUtils;
import com.sunseeker.mall.ware.entity.WareSkuEntity;
import com.sunseeker.common.to.SkuHasStockVo;
import com.sunseeker.mall.ware.vo.SkuMinusStockVo;
import com.sunseeker.mall.ware.vo.WareSkuLockVo;

import java.util.List;
import java.util.Map;

/**
 * 商品库存
 *
 * @author sunseeker
 * @email 1522292372@qq.com
 * @date 2021-03-23 23:06:41
 */
public interface WareSkuService extends IService<WareSkuEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void addStock(Long skuId, Long wareId, Integer skuNum);

    List<SkuHasStockVo> getSkuHasStocks(List<Long> ids);

    Boolean orderLockStock(WareSkuLockVo lockVo);

    void unlock(StockLockedTo stockLockedTo);

    void unlock(OrderTo orderTo);

    void updateStock(SkuMinusStockVo skuMinusStockVo);
}

