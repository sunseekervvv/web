package com.sunseeker.mall.ware.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sunseeker.common.utils.PageUtils;
import com.sunseeker.mall.ware.entity.WareInfoEntity;
import com.sunseeker.mall.ware.vo.FareVo;

import java.util.Map;

/**
 * 仓库信息
 *
 * @author sunseeker
 * @email 1522292372@qq.com
 * @date 2021-03-23 23:06:41
 */
public interface WareInfoService extends IService<WareInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);
    FareVo getFare(Long addrId);
}

