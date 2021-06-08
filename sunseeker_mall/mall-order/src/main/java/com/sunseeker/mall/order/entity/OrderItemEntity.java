package com.sunseeker.mall.order.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 订单项信息
 * 
 * @author sunseeker
 * @email 1522292372@qq.com
 * @date 2021-03-23 23:03:00
 */
@Data
@TableName("oms_order_item")
public class OrderItemEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId
	private Long id;
	/**
	 * order_id
	 */
	private Long orderId;
	/**
	 * order_sn
	 */
	private String orderSn;
	/**
	 * spu_id
	 */
	private Long spuId;
	/**
	 * spu_name
	 */
	private String spuName;
	/**
	 * spu_pic
	 */
	private String spuPic;
	/**
	 * 品牌
	 */
	private String spuBrand;
	/**
	 * 商品分类id
	 */
	private Long categoryId;
	/**
	 * 商品sku编号
	 */
	private Long skuId;
	/**
	 * 商品sku名字
	 */
	private String skuName;
	/**
	 * 商品sku图片
	 */
	private String skuPic;
	/**
	 * 商品sku价格
	 */
	private BigDecimal skuPrice;
	/**
	 * 商品购买的数量
	 */
	private Integer skuQuantity;
	/**
	 * 商品销售属性组合（JSON）
	 */
	private String skuAttrsVals;
	/**
	 * 商品促销分解金额
	 */
	private BigDecimal promotionAmount;
	/**
	 * 优惠券优惠分解金额
	 */
	private BigDecimal couponAmount;
	/**
	 * 积分优惠分解金额
	 */
	private BigDecimal integrationAmount;
	/**
	 * 该商品经过优惠后的分解金额
	 */
	private BigDecimal realAmount;
	/**
	 * 赠送积分
	 */
	private Integer giftIntegration;
	/**
	 * 赠送成长值
	 */
	private Integer giftGrowth;

	/**
	 * 订单状态【0->待付款；1->待发货；2->已发货；3->已完成；4->已关闭；5->无效订单】
	 */
	private Integer status;

	private String seller;

}
