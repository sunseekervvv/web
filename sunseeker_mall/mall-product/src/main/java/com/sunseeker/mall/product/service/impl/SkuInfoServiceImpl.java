package com.sunseeker.mall.product.service.impl;

import com.alibaba.fastjson.TypeReference;
import com.sunseeker.common.utils.PageUtils;
import com.sunseeker.common.utils.Query;
import com.sunseeker.mall.product.dao.SkuInfoDao;
import com.sunseeker.mall.product.entity.SkuImagesEntity;
import com.sunseeker.mall.product.entity.SkuInfoEntity;
import com.sunseeker.mall.product.entity.SpuInfoDescEntity;
import com.sunseeker.mall.product.service.*;
import com.sunseeker.mall.product.vo.SkuItemSaleAttrVo;
import com.sunseeker.mall.product.vo.SkuItemVo;
import com.sunseeker.mall.product.vo.SkuSaleVo;
import com.sunseeker.mall.product.vo.SpuItemAttrGroupVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadPoolExecutor;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.util.StringUtils;


@Service("skuInfoService")
public class SkuInfoServiceImpl extends ServiceImpl<SkuInfoDao, SkuInfoEntity> implements SkuInfoService {
    @Autowired
    private SkuImagesService skuImagesService;

    @Autowired
    private SkuSaleAttrValueService skuSaleAttrValueService;

    @Autowired
    private SpuInfoDescService spuInfoDescService;

    @Autowired
    private ProductAttrValueService productAttrValueService;

    //@Autowired
    //private SeckillFeignService seckillFeignService;

    @Autowired
    private ThreadPoolExecutor executor;


    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SkuInfoEntity> page = this.page(
                new Query<SkuInfoEntity>().getPage(params),
                new QueryWrapper<SkuInfoEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public void saveSkuInfo(SkuInfoEntity skuInfoEntity) {
        this.baseMapper.insert(skuInfoEntity);
    }

    @Override
    public PageUtils queryPageByCondition(Map<String, Object> params) {
        QueryWrapper<SkuInfoEntity> queryWrapper = new QueryWrapper<>();
        /**
         * key:
         * catelogId: 0
         * brandId: 0
         * min: 0
         * max: 0
         */
        String key = (String) params.get("key");
        if(!StringUtils.isEmpty(key)){
            queryWrapper.and((wrapper)->{
               wrapper.eq("sku_id",key).or().like("sku_name",key);
            });
        }

        String catelogId = (String) params.get("catelogId");
        if(!StringUtils.isEmpty(catelogId)&&!"0".equalsIgnoreCase(catelogId)){

            queryWrapper.eq("catalog_id",catelogId);
        }

        String brandId = (String) params.get("brandId");
        if(!StringUtils.isEmpty(brandId)&&!"0".equalsIgnoreCase(catelogId)){
            queryWrapper.eq("brand_id",brandId);
        }

        String min = (String) params.get("min");
        if(!StringUtils.isEmpty(min)){
            queryWrapper.ge("price",min);
        }

        String max = (String) params.get("max");

        if(!StringUtils.isEmpty(max)  ){
            try{
                BigDecimal bigDecimal = new BigDecimal(max);

                if(bigDecimal.compareTo(new BigDecimal("0"))==1){
                    queryWrapper.le("price",max);
                }
            }catch (Exception e){

            }

        }


        IPage<SkuInfoEntity> page = this.page(
                new Query<SkuInfoEntity>().getPage(params),
                queryWrapper
        );

        return new PageUtils(page);
    }

    @Override
    public List<SkuInfoEntity> getSkusBySpuId(Long spuId) {
        List<SkuInfoEntity> list = this.list(new QueryWrapper<SkuInfoEntity>().eq("spu_id",spuId));
        return list;
    }

    @Cacheable(value = {"skuItem"},key = "#skuId")
    @Override
    public SkuItemVo item(Long skuId) {
        SkuItemVo skuItemVo = new SkuItemVo();
        CompletableFuture<SkuInfoEntity> infoFuture = CompletableFuture.supplyAsync(() -> {
            //1、sku基本信息的获取  pms_sku_info
            SkuInfoEntity skuInfoEntity = this.getById(skuId);
            skuItemVo.setInfo(skuInfoEntity);
            return skuInfoEntity;
        }, executor);

        //2、sku的图片信息    pms_sku_images
        CompletableFuture<Void> imageFuture = CompletableFuture.runAsync(() -> {
            List<SkuImagesEntity> skuImagesEntities = skuImagesService.getImagesBySkuId(skuId);
            skuItemVo.setImages(skuImagesEntities);
        }, executor);


        //3、获取spu的销售属性组合-> 依赖1 获取spuId
        CompletableFuture<Void> saleFuture = infoFuture.thenAcceptAsync((info) -> {
            List<SkuItemSaleAttrVo> saleAttrVos = skuSaleAttrValueService.getSaleAttrsBySpuId(info.getSpuId());
            skuItemVo.setSaleAttr(saleAttrVos);
        }, executor);


        //4、获取spu的介绍-> 依赖1 获取spuId
        CompletableFuture<Void> descFuture = infoFuture.thenAcceptAsync((info) -> {
            SpuInfoDescEntity byId = spuInfoDescService.getById(info.getSpuId());
            skuItemVo.setDesc(byId);
        }, executor);


        //5、获取spu的规格参数信息-> 依赖1 获取spuId catalogId
        CompletableFuture<Void> attrFuture = infoFuture.thenAcceptAsync((info) -> {
            List<SpuItemAttrGroupVo> spuItemAttrGroupVos=productAttrValueService.getProductGroupAttrsBySpuId(info.getSpuId(), info.getCatalogId());
            skuItemVo.setGroupAttrs(spuItemAttrGroupVos);
        }, executor);

        ////6、秒杀商品的优惠信息
        //CompletableFuture<Void> seckFuture = CompletableFuture.runAsync(() -> {
        //    R r = seckillFeignService.getSeckillSkuInfo(skuId);
        //    if (r.getCode() == 0) {
        //        SeckillSkuVo seckillSkuVo = r.getData(new TypeReference<SeckillSkuVo>() {
        //        });
        //        long current = System.currentTimeMillis();
        //        //如果返回结果不为空且活动未过期，设置秒杀信息
        //        if (seckillSkuVo != null&&current<seckillSkuVo.getEndTime()) {
        //            skuItemVo.setSeckillSkuVo(seckillSkuVo);
        //        }
        //    }
        //}, executor);

        //等待所有任务执行完成
        try {
            CompletableFuture.allOf(imageFuture, saleFuture, descFuture, attrFuture).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        return skuItemVo;
    }

    @Override
    public void updateSale(SkuSaleVo skuSaleVo) {
        SkuInfoEntity sku_id = this.getOne(new QueryWrapper<SkuInfoEntity>().eq("sku_id", skuSaleVo.getSkuId()));
        sku_id.setSaleCount(sku_id.getSaleCount()+skuSaleVo.getCount());
        this.saveOrUpdate(sku_id);
    }

}