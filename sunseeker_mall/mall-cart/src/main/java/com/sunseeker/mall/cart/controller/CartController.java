package com.sunseeker.mall.cart.controller;

import com.sunseeker.common.utils.R;
import com.sunseeker.mall.cart.service.CartService;
import com.sunseeker.mall.cart.vo.CartAddVo;
import com.sunseeker.mall.cart.vo.CartItemVo;
import com.sunseeker.mall.cart.vo.CartVo;
import io.lettuce.core.ScriptOutputType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("cart")
public class CartController {
    @Autowired
    private CartService cartService;

    @RequestMapping("/cartList")
    public R getCartList() {
        CartVo cartVo=cartService.getCart();
        return R.ok().put("data",cartVo);
    }

    /**
     * 添加商品到购物车
     * RedirectAttributes.addFlashAttribute():将数据放在session中，可以在页面中取出，但是只能取一次
     * RedirectAttributes.addAttribute():将数据放在url后面
     * @return
     */
    @RequestMapping("/addCartItem")
    public R addCartItem(@RequestBody CartAddVo cartAddVo) {
        CartItemVo cartItemVo = cartService.addCartItem(cartAddVo);
        System.out.println(cartAddVo);
        return R.ok().put("data", cartItemVo);

    }

    //@RequestMapping("/addCartItemSuccess")
    //public String addCartItemSuccess(@RequestParam("skuId") Long skuId,Model model) {
    //    CartItemVo cartItemVo = cartService.getCartItem(skuId);
    //    model.addAttribute("cartItem", cartItemVo);
    //    return "success";
    //}


    @RequestMapping("/checkCart")
    public R checkCart(@RequestParam("isChecked") Integer isChecked,@RequestParam("skuId")Long skuId) {
        cartService.checkCart(skuId, isChecked);
        return R.ok();
    }
    @RequestMapping("/selectAll")
    public R selectAll(@RequestParam("isChecked") Integer isChecked) {
        cartService.selectAll(isChecked);
        return R.ok();
    }

    @RequestMapping("/countItem")
    public R changeItemCount(@RequestParam("skuId") Long skuId, @RequestParam("num") Integer num) {
        cartService.changeItemCount(skuId, num);
        return R.ok();
    }

    @RequestMapping("/deleteItem")
    public R deleteItem(@RequestParam("skuId") Long skuId) {
        cartService.deleteItem(skuId);
        return R.ok();
    }

    @RequestMapping("/deleteSelect")
    public R deleteSelect(@RequestParam("skuIds") Long[] skuIds) {
        cartService.deleteSelect(skuIds);
        return R.ok();
    }


    @ResponseBody
    @RequestMapping("/getCheckedItems")
    public List<CartItemVo> getCheckedItems() {
        return cartService.getCheckedItems();
    }
}
