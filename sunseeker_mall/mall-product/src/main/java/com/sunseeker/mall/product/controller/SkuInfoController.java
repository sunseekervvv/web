package com.sunseeker.mall.product.controller;

import java.util.Arrays;
import java.util.Map;

import com.sunseeker.mall.product.vo.SkuItemVo;
import com.sunseeker.mall.product.vo.SkuSaleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.sunseeker.mall.product.entity.SkuInfoEntity;
import com.sunseeker.mall.product.service.SkuInfoService;
import com.sunseeker.common.utils.PageUtils;
import com.sunseeker.common.utils.R;



/**
 * sku信息
 *
 * @author sunseeker
 * @email 1522292372@qq.com
 * @date 2021-03-24 10:46:54
 */
@RestController
@RequestMapping("product/skuinfo")
public class SkuInfoController {
    @Autowired
    private SkuInfoService skuInfoService;


    @GetMapping("/skuItem/{skuId}")
    public R skuItem(@PathVariable("skuId") Long skuId) {
        SkuItemVo skuItemVo=skuInfoService.item(skuId);
        return R.ok().put("data", skuItemVo);
    }

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("product:skuinfo:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = skuInfoService.queryPageByCondition(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{skuId}")
    //@RequiresPermissions("product:skuinfo:info")
    public R info(@PathVariable("skuId") Long skuId){
        SkuInfoEntity skuInfo = skuInfoService.getById(skuId);

        return R.ok().put("skuInfo", skuInfo);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("product:skuinfo:save")
    public R save(@RequestBody SkuInfoEntity skuInfo){
        skuInfoService.save(skuInfo);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody SkuInfoEntity skuInfo){
        skuInfoService.updateById(skuInfo);

        return R.ok();
    }

    @RequestMapping("/updateSales")
    public R updateSales(@RequestBody SkuSaleVo skuSaleVo){
        skuInfoService.updateSale(skuSaleVo);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("product:skuinfo:delete")
    public R delete(@RequestBody Long[] skuIds){
        skuInfoService.removeByIds(Arrays.asList(skuIds));

        return R.ok();
    }

}
