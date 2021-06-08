package com.sunseeker.mall.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sunseeker.common.utils.PageUtils;
import com.sunseeker.mall.member.entity.MemberStatisticsInfoEntity;

import java.util.Map;

/**
 * 会员统计信息
 *
 * @author sunseeker
 * @email 1522292372@qq.com
 * @date 2021-03-23 22:49:02
 */
public interface MemberStatisticsInfoService extends IService<MemberStatisticsInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

