package com.sunseeker.mall.order.service.impl;

import com.sunseeker.common.utils.PageUtils;
import com.sunseeker.common.utils.Query;
import com.sunseeker.mall.order.dao.PaymentInfoDao;
import com.sunseeker.mall.order.entity.PaymentInfoEntity;
import com.sunseeker.mall.order.service.PaymentInfoService;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


@Service("paymentInfoService")
public class PaymentInfoServiceImpl extends ServiceImpl<PaymentInfoDao, PaymentInfoEntity> implements PaymentInfoService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<PaymentInfoEntity> page = this.page(
                new Query<PaymentInfoEntity>().getPage(params),
                new QueryWrapper<PaymentInfoEntity>()
        );

        return new PageUtils(page);
    }

}