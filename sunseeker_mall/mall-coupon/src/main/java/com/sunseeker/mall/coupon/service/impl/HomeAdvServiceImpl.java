package com.sunseeker.mall.coupon.service.impl;

import com.sunseeker.common.utils.PageUtils;
import com.sunseeker.common.utils.Query;
import com.sunseeker.mall.coupon.dao.HomeAdvDao;
import com.sunseeker.mall.coupon.entity.HomeAdvEntity;
import com.sunseeker.mall.coupon.service.HomeAdvService;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


@Service("homeAdvService")
public class HomeAdvServiceImpl extends ServiceImpl<HomeAdvDao, HomeAdvEntity> implements HomeAdvService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<HomeAdvEntity> page = this.page(
                new Query<HomeAdvEntity>().getPage(params),
                new QueryWrapper<HomeAdvEntity>()
        );

        return new PageUtils(page);
    }

}