package com.sunseeker.mall.product.dao;

import com.sunseeker.mall.product.entity.CategoryEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品三级分类
 * 
 * @author sunseeker
 * @email 1522292372@qq.com
 * @date 2021-03-23 20:54:54
 */
@Mapper
public interface CategoryDao extends BaseMapper<CategoryEntity> {
	
}
