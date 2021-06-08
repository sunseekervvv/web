package com.sunseeker.mall.cart.service;


import com.sunseeker.mall.cart.vo.CartAddVo;
import com.sunseeker.mall.cart.vo.CartItemVo;
import com.sunseeker.mall.cart.vo.CartVo;

import java.util.List;

public interface CartService {
    CartItemVo addCartItem(CartAddVo cartAddVo);

    CartItemVo getCartItem(Long skuId);

    CartVo getCart();

    void checkCart(Long skuId, Integer isChecked);

    void changeItemCount(Long skuId, Integer num);

    void deleteItem(Long skuId);

    List<CartItemVo> getCheckedItems();

    void selectAll(Integer isChecked);

    void deleteSelect(Long[] skuIds);
}
