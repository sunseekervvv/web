package com.sunseeker.mall.product.dao;

import com.sunseeker.mall.product.entity.CommentReplayEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品评价回复关系
 * 
 * @author sunseeker
 * @email 1522292372@qq.com
 * @date 2021-03-24 10:46:54
 */
@Mapper
public interface CommentReplayDao extends BaseMapper<CommentReplayEntity> {
	
}
