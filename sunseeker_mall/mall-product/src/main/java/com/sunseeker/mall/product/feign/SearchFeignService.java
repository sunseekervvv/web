package com.sunseeker.mall.product.feign;

import com.sunseeker.common.to.es.SkuEsModel;
import com.sunseeker.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient("mall-search")
public interface SearchFeignService {
    @PostMapping("/search/save/product") // ElasticSaveController
    R productStatusUp(@RequestBody List<SkuEsModel> skuEsModels);
}
