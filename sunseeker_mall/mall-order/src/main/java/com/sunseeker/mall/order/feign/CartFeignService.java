package com.sunseeker.mall.order.feign;

import com.sunseeker.mall.order.vo.OrderItemVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@FeignClient("mall-cart")
public interface CartFeignService {

    @ResponseBody
    @RequestMapping("/cart/getCheckedItems")
    List<OrderItemVo> getCheckedItems();
}
