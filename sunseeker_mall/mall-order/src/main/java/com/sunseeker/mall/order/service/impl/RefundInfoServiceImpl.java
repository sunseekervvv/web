package com.sunseeker.mall.order.service.impl;

import com.sunseeker.common.utils.PageUtils;
import com.sunseeker.common.utils.Query;
import com.sunseeker.mall.order.dao.RefundInfoDao;
import com.sunseeker.mall.order.entity.RefundInfoEntity;
import com.sunseeker.mall.order.service.RefundInfoService;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


@Service("refundInfoService")
public class RefundInfoServiceImpl extends ServiceImpl<RefundInfoDao, RefundInfoEntity> implements RefundInfoService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<RefundInfoEntity> page = this.page(
                new Query<RefundInfoEntity>().getPage(params),
                new QueryWrapper<RefundInfoEntity>()
        );

        return new PageUtils(page);
    }

}