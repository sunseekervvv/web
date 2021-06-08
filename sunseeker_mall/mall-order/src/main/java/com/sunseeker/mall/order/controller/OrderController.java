package com.sunseeker.mall.order.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.sunseeker.common.exception.NoStockException;
import com.sunseeker.common.utils.PageUtils;
import com.sunseeker.common.utils.R;
import com.sunseeker.mall.order.entity.OrderEntity;
import com.sunseeker.mall.order.service.OrderService;
import com.sunseeker.mall.order.vo.OrderConfirmVo;
import com.sunseeker.mall.order.vo.OrderSubmitVo;
import com.sunseeker.mall.order.vo.SubmitOrderResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


/**
 * 订单
 */
@RestController
@RequestMapping("order/order")
public class OrderController {
    @Autowired
    private OrderService orderService;


    @RequestMapping("/toTrade")
    public R toTrade() {
        OrderConfirmVo confirmVo = orderService.confirmOrder();
        return R.ok().put("data", confirmVo);
    }

    @RequestMapping("/submitOrder")
    public R submitOrder(OrderSubmitVo submitVo) {
        try{
            SubmitOrderResponseVo responseVo=orderService.submitOrder(submitVo);
            Integer code = responseVo.getCode();
            if (code==0){
                return R.ok().put("order", responseVo.getOrder());
            }else {
                String msg = "Order failed;";
                switch (code) {
                    case 1:
                        msg += "Repeat Order";
                        break;
                    case 2:
                        msg += "Order Price changed";
                        break;
                }
                return R.error(201,msg);
            }
        }catch (Exception e){
            if (e instanceof NoStockException){
                String msg = "No stock";
                return R.error(201,msg);
            }
            return R.error();
        }
    }

    /**
     * 获取当前用户的所有订单
     * @return
     */
    @RequestMapping("/memberOrder")
    public R memberOrder(@RequestParam(value = "pageNum",required = false,defaultValue = "0") Integer pageNum){
        Map<String, Object> params = new HashMap<>();
        params.put("page", pageNum.toString());
        PageUtils page = orderService.getMemberOrderPage(params);
        return R.ok().put("page", page);
    }

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = orderService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
		OrderEntity order = orderService.getById(id);

        return R.ok().put("order", order);
    }

    @RequestMapping("/infoByOrderSn/{OrderSn}")
    public R infoByOrderSn(@PathVariable("OrderSn") String OrderSn){
        OrderEntity order = orderService.getOrderByOrderSn(OrderSn);

        return R.ok().put("order", order);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody OrderEntity order){
		orderService.save(order);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody OrderEntity order){
		orderService.updateById(order);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
		orderService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
