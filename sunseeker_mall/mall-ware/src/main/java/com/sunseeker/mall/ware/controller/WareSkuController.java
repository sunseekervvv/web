package com.sunseeker.mall.ware.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.sunseeker.common.exception.BizCodeEnum;
import com.sunseeker.common.exception.NoStockException;
import com.sunseeker.common.to.SkuHasStockVo;
import com.sunseeker.mall.ware.vo.SkuMinusStockVo;
import com.sunseeker.mall.ware.vo.WareSkuLockVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.sunseeker.mall.ware.entity.WareSkuEntity;
import com.sunseeker.mall.ware.service.WareSkuService;
import com.sunseeker.common.utils.PageUtils;
import com.sunseeker.common.utils.R;



/**
 * 商品库存
 *
 * @author sunseeker
 * @email 1522292372@qq.com
 * @date 2021-03-23 23:06:41
 */
@RestController
@RequestMapping("ware/waresku")
public class WareSkuController {
    @Autowired
    private WareSkuService wareSkuService;


    /**
     * 下订单时锁库存
     * @param lockVo
     * @return
     */
    @RequestMapping("/lock/order")
    public R orderLockStock(@RequestBody WareSkuLockVo lockVo) {
        try {
            Boolean lock = wareSkuService.orderLockStock(lockVo);
            return R.ok();
        } catch (NoStockException e) {
            return R.error(BizCodeEnum.NO_STOCK_EXCEPTION.getCode(), BizCodeEnum.NO_STOCK_EXCEPTION.getMsg());
        }
    }

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = wareSkuService.queryPage(params);

        return R.ok().put("page", page);
    }


    @RequestMapping("/getSkuHasStocks")
    public List<SkuHasStockVo> getSkuHasStocks(@RequestBody List<Long> ids) {
        return wareSkuService.getSkuHasStocks(ids);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        WareSkuEntity wareSku = wareSkuService.getById(id);

        return R.ok().put("wareSku", wareSku);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody WareSkuEntity wareSku){
        wareSkuService.save(wareSku);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody WareSkuEntity wareSku){
        wareSkuService.updateById(wareSku);

        return R.ok();
    }

    @RequestMapping("/updateStock")
    public R updateStock(@RequestBody SkuMinusStockVo skuMinusStockVo){
        wareSkuService.updateStock(skuMinusStockVo);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        wareSkuService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
