package com.sunseeker.mall.order.service.impl;

import com.sunseeker.common.utils.PageUtils;
import com.sunseeker.common.utils.Query;
import com.sunseeker.mall.order.dao.OrderItemDao;
import com.sunseeker.mall.order.entity.OrderEntity;
import com.sunseeker.mall.order.entity.OrderItemEntity;
import com.sunseeker.mall.order.feign.ProductFeignService;
import com.sunseeker.mall.order.feign.WareFeignService;
import com.sunseeker.mall.order.service.OrderItemService;
import com.sunseeker.mall.order.service.OrderService;
import com.sunseeker.mall.order.vo.OrderItemConfirmVo;
import com.sunseeker.mall.order.vo.OrderSnVo;
import com.sunseeker.mall.order.vo.SkuMinusStockVo;
import com.sunseeker.mall.order.vo.SkuSaleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


@Service("orderItemService")
public class OrderItemServiceImpl extends ServiceImpl<OrderItemDao, OrderItemEntity> implements OrderItemService {
    @Autowired
    OrderService orderService;

    @Autowired
    WareFeignService wareFeignService;

    @Autowired
    ProductFeignService productFeignService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<OrderItemEntity> page = this.page(
                new Query<OrderItemEntity>().getPage(params),
                new QueryWrapper<OrderItemEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public void updateOrderSn(OrderSnVo orderSn) {

        List<OrderItemEntity> order_sn = baseMapper.selectList(new QueryWrapper<OrderItemEntity>().eq("order_sn", orderSn.getOrderSn()));
        for(OrderItemEntity o: order_sn){
            o.setStatus(1);
            SkuMinusStockVo skuMinusStockVo = new SkuMinusStockVo();
            skuMinusStockVo.setSkuId(o.getSkuId());
            skuMinusStockVo.setCount(o.getSkuQuantity());
            wareFeignService.updateStock(skuMinusStockVo);
        }
        OrderEntity orderEntity = orderService.getOne(new QueryWrapper<OrderEntity>().eq("order_sn", orderSn.getOrderSn()));
        orderEntity.setStatus(1);

        orderService.saveOrUpdate(orderEntity);
        this.saveOrUpdateBatch(order_sn);
    }

    @Override
    public void confirm(OrderItemConfirmVo orderItemConfirmVo) {
        OrderItemEntity id = this.getOne(new QueryWrapper<OrderItemEntity>().eq("id", orderItemConfirmVo.getId()));
        id.setStatus(3);
        this.saveOrUpdate(id);
        SkuSaleVo skuSaleVo = new SkuSaleVo();
        skuSaleVo.setSkuId(id.getSkuId());
        skuSaleVo.setCount(id.getSkuQuantity());
        productFeignService.updateSales(skuSaleVo);
    }

}