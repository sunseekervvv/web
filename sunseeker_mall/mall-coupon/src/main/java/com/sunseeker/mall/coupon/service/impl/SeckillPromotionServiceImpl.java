package com.sunseeker.mall.coupon.service.impl;

import com.sunseeker.common.utils.PageUtils;
import com.sunseeker.common.utils.Query;
import com.sunseeker.mall.coupon.dao.SeckillPromotionDao;
import com.sunseeker.mall.coupon.entity.SeckillPromotionEntity;
import com.sunseeker.mall.coupon.service.SeckillPromotionService;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


@Service("seckillPromotionService")
public class SeckillPromotionServiceImpl extends ServiceImpl<SeckillPromotionDao, SeckillPromotionEntity> implements SeckillPromotionService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SeckillPromotionEntity> page = this.page(
                new Query<SeckillPromotionEntity>().getPage(params),
                new QueryWrapper<SeckillPromotionEntity>()
        );

        return new PageUtils(page);
    }

}