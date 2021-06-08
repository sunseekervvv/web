package com.sunseeker.mall.cart.feign;

import com.sunseeker.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient("mall-product")
public interface ProductFeignService {
    @RequestMapping("product/skuinfo/info/{skuId}")
    R info(@PathVariable("skuId") Long skuId);

    @RequestMapping("product/skusaleattrvalue/getSkuSaleAttrValuesAsString")
    List<String> getSkuSaleAttrValuesAsString(@RequestBody Long skuId);
}
