package com.sunseeker.mall.order.controller;

import java.util.Arrays;
import java.util.Map;

import com.sunseeker.common.utils.PageUtils;
import com.sunseeker.common.utils.R;
import com.sunseeker.mall.order.vo.OrderItemConfirmVo;
import com.sunseeker.mall.order.vo.OrderSnVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.sunseeker.mall.order.entity.OrderItemEntity;
import com.sunseeker.mall.order.service.OrderItemService;



/**
 * 订单项信息
 */
@RestController
@RequestMapping("order/orderitem")
public class OrderItemController {
    @Autowired
    private OrderItemService orderItemService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = orderItemService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
		OrderItemEntity orderItem = orderItemService.getById(id);

        return R.ok().put("orderItem", orderItem);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody OrderItemEntity orderItem){
		orderItemService.save(orderItem);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody OrderItemEntity orderItem){
		orderItemService.updateById(orderItem);

        return R.ok();
    }

    @RequestMapping("/confirm")
    public R confirm(@RequestBody OrderItemConfirmVo orderItemConfirmVo){
        orderItemService.confirm(orderItemConfirmVo);

        return R.ok();
    }

    @PostMapping("/updateOrderSn")
    public R updateOrderSn(@RequestBody OrderSnVo orderSn){
        orderItemService.updateOrderSn(orderSn);

        return R.ok();
    }
    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestParam("ids") Long[] ids){
		orderItemService.removeByIds(Arrays.asList(ids));
        return R.ok();
    }

}
