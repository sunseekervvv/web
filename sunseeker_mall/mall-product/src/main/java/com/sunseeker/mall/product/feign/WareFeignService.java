package com.sunseeker.mall.product.feign;

import com.sunseeker.common.to.SkuHasStockVo;
import com.sunseeker.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient("mall-ware")
public interface WareFeignService {
    // sku是否有库存
    @PostMapping("/ware/waresku/hasstock")
    List<SkuHasStockVo> getSkuHasStock(@RequestBody List<Long> ids);
}
