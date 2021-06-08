package com.sunseeker.mall.coupon.service.impl;

import com.sunseeker.common.utils.PageUtils;
import com.sunseeker.common.utils.Query;
import com.sunseeker.mall.coupon.dao.CouponSpuCategoryRelationDao;
import com.sunseeker.mall.coupon.entity.CouponSpuCategoryRelationEntity;
import com.sunseeker.mall.coupon.service.CouponSpuCategoryRelationService;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


@Service("couponSpuCategoryRelationService")
public class CouponSpuCategoryRelationServiceImpl extends ServiceImpl<CouponSpuCategoryRelationDao, CouponSpuCategoryRelationEntity> implements CouponSpuCategoryRelationService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CouponSpuCategoryRelationEntity> page = this.page(
                new Query<CouponSpuCategoryRelationEntity>().getPage(params),
                new QueryWrapper<CouponSpuCategoryRelationEntity>()
        );

        return new PageUtils(page);
    }

}